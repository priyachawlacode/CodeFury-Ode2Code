
<!-------------------------------------
            Search Modal
   	--------------------------------------->


	<div class="modal fade" id="searchModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<form action="${pageContext.request.contextPath}/SearchServlet"
						method="POST">
						<div class="input-group mb-3">
							<label class="input-group-text" for="inputGroupSelect01">Search
								By</label> <select name="type" class="form-select" id="type">
								<option selected>Choose...</option>
								<option value="username">Username</option>
								<option value="emailId">Email</option>
								<option value="city">City</option>
								<option value="state">State</option>
								<option value="country">Country</option>
								<option value="company">Company</option>
							</select>
						</div>
						<div class="mb-3">
							<input type="text" name="value" aria-label="Last name"
								class="form-control" placeholder="Search">
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Search</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
			function Search() {
				// body...
				$('#searchModal').modal('show');
			}
		</script>
