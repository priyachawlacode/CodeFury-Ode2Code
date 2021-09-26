package com.hsbc.networking.dao.AdminDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hsbc.networking.model.Admin;
import com.hsbc.networking.model.User;
import com.hsbc.networking.dao.DBUtil.DBUtil;

public class AdminDaoImpl implements AdminDao {

	private static Connection conn;
	private static PreparedStatement getDisabledList, getAllUsers, getDeleteList, getCount, disableUser,
			setUserActivity, checkIfNew, updateUserAct;
	private static String path = "http://localhost:8080/ContactNetworkingWebApp/admin.xml";
	static {
		try {
			conn = DBUtil.getDBConnection();
			getDisabledList = conn.prepareStatement(
					"select b.blockUserId,count(b.user_id) as totalBlocks,u.country from blockedUser b,users u where b.blockUserId=u.user_id Group by b.blockUserId");

			getAllUsers = conn.prepareStatement("select *from users");

			getDeleteList = conn.prepareStatement(
					"select u.user_id,v.country from useractivity u ,users v  where u.user_id=v.user_id and timestampdiff(DAY, lastseen, now()) > ?;");

			getCount = conn.prepareStatement("select count(user_id) as totalUsers from users");

			disableUser = conn.prepareStatement("update users set isDisabled = 1 where user_id=? ");

			setUserActivity = conn
					.prepareStatement("insert into userActivity(user_id,lastSeen,activityhours) values(?,?,?)");

			checkIfNew = conn.prepareStatement("select 1 as value from userActivity where user_id = ?");

			updateUserAct = conn
					.prepareStatement("update userActivity set lastSeen = ? , activityhours =? where user_id = ?");

		} catch (SQLException e) {
			System.out.println("DB CONN ERROR: " + e.getMessage());
		}
	}

	@Override
	public List<User> getDisableList() {
		List<User> userToDisable = new ArrayList<User>();
		try {
			ResultSet result = getDisabledList.executeQuery();
			while (result.next()) {
				if (result.getInt("totalBlocks") >= 3) {
					User user = new User();
					user.setUserId(result.getString("blockUserId"));

					user.setCountry(result.getString("country"));
					userToDisable.add(user);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userToDisable;
	}

	@Override
	public List<User> userAll() {
		List<User> userTotal = new ArrayList<User>();
		try {
			ResultSet rs = getAllUsers.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setFullName(rs.getString("name"));
				u.setDob(rs.getDate("date"));
				u.setAddress(rs.getString("address"));
				userTotal.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userTotal;
	}

	@Override
	public List<User> getDeleteList(int days) {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<User>();
		try {
			getDeleteList.setInt(1, days);
			ResultSet rs = getDeleteList.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setCity(rs.getString("country"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public int returnUserCount() {
		int cnt = 0;

		try {
			ResultSet rs = getCount.executeQuery();
			while (rs.next()) {
				cnt = rs.getInt("totalUsers");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public boolean adminLogin(String username, String password) {
		boolean usrandpasMatch = true;
		String uname = "";
		String pwd = "";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document doc = (Document) builder.parse(path);
			NodeList nodeList = ((org.w3c.dom.Document) doc).getElementsByTagName("admin");
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = (Node) nodeList.item(itr);
				// System.out.println("\nNode Name :" + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					uname = eElement.getElementsByTagName("username").item(0).getTextContent();
					pwd = eElement.getElementsByTagName("password").item(0).getTextContent();

					if (username.equals(uname) && (password.equals(pwd)))
						return usrandpasMatch;
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void disableUser(String user_id) {
		try {
			disableUser.setString(1, user_id);
			disableUser.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Admin getAdminInfo(String username) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		String name = "", email = "", phone = "";
		Admin ad = new Admin();
		try {

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = (Document) builder.parse(path);
			NodeList nodeList = ((org.w3c.dom.Document) doc).getElementsByTagName("admin");
			// nodeList is not iterable, so we are using for loop
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = (Node) nodeList.item(itr);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					if (username.equals(eElement.getElementsByTagName("username").item(0).getTextContent())) {
						ad.setUsername(username);
						name = eElement.getElementsByTagName("name").item(0).getTextContent();
						ad.setName(name);
						email = eElement.getElementsByTagName("email").item(0).getTextContent();
						ad.setEmail(email);
						phone = eElement.getElementsByTagName("phone").item(0).getTextContent();
						ad.setPhoneNo(phone);
					}

				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return ad;
	}

	@Override
	public boolean addUserActivity(String userId) {
		// System.out.println("Inside addUserActivity start Dao");
		boolean added = false;
		Date value = new Date();
		int isNew = 0;
		try {
			checkIfNew.setString(1, userId);
			try {
				ResultSet rs = checkIfNew.executeQuery();
				while (rs.next()) {
					isNew = rs.getInt("value");
				}
				// System.out.println("isNew=" + isNew);
			} catch (SQLException e) {

				e.printStackTrace();
			}
			if (isNew == 0) {
				setUserActivity.setString(1, userId);
				setUserActivity.setTimestamp(2, new Timestamp(value.getTime()));
				setUserActivity.setFloat(3, value.getTime());
				setUserActivity.execute();
				added = true;
			} else if (isNew == 1) {
				updateUserAct.setTimestamp(1, new Timestamp(value.getTime()));
				updateUserAct.setFloat(2, value.getTime());
				updateUserAct.setString(3, userId);
				updateUserAct.executeUpdate();
				added = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return added;
	}

}