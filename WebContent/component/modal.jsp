<div class="modal fade" id="exampleModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="exampleModalLabel">Add New
					Block Cheatsheet</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form class="row g-3" id="cheatsheetForm">
					<div class="col-12">
						<label for="inputAddress" class="form-label">Title</label>
						<input type="text" class="form-control" id="inputAddress">
					</div>
					<div class="col-md-6">
						<label for="column1" class="form-label">Column 1</label>
						<input type="text" class="form-control" id="column1">
					</div>
					<div class="col-md-6">
						<label for="column2" class="form-label">Column 2</label>
						<input type="text" class="form-control" id="column2">
					</div>
					<div id="additionalFields"></div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>
