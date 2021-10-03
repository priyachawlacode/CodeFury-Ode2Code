package com.hsbc.networking.dao.BlockDao;

public class BlockDaoFactory {
	public static BlockDao getDaoInstance() {
		return new BlockDaoImpl();
	}
}
