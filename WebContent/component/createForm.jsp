<form class="container mx-auto row g-3">
	<h2 class="title">Create New Cheatsheet</h2>
	<div class="col-12">
		<label for="inputAddress" class="form-label">Description</label> <input
			type="text" class="form-control" id="inputAddress">
	</div>
	<div class="col-md-6">
		<label for="inputEmail4" class="form-label">Color</label> <input
			type="email" class="form-control" id="inputEmail4">
	</div>
	<div class="col-md-6">
		<label for="inputState" class="form-label">Language</label> <select
			id="inputState" class="form-select">
			<option selected>Choose...</option>
			<option>...</option>
		</select>
	</div>
	<div class="col-md-6">
		<label for="inputState" class="form-label">Style</label> <select
			id="inputState" class="form-select">
			<option selected>Choose...</option>
			<option>...</option>
		</select>
	</div>
	<div class="col-md-6">
		<label for="inputState" class="form-label">Type</label> <select
			id="inputState" class="form-select">
			<option selected>Choose...</option>
			<option>...</option>
		</select>
	</div>
	<div class="col-12">
		<label for="inputState" class="form-label">Section</label> <select
			id="inputState" class="form-select">
			<option selected>Choose...</option>
			<option>...</option>
		</select>
	</div>
	<div class="col-12">
		<label for="inputState" class="form-label">Subsection</label> <select
			id="inputState" class="form-select">
			<option selected>Choose...</option>
			<option>...</option>
		</select>
	</div>
	<div class="col-12">
		<label for="inputAddress" class="form-label">Content</label> <input
			type="text" class="form-control" id="inputAddress">
	</div>
	<div class="col-12">
		<div type="button"  data-bs-toggle="modal"
			data-bs-target="#exampleModal">
			<i class="bi bi-plus-square-fill"></i> <label
				class="form-check-label ml-2" for="gridCheck">Add New Block </label>
		</div>
	</div>
	<div class="col-12 d-flex justify-content-center my-4">
    <button type="submit" class="btn btn-secondary">Submit</button>
  </div>
</form>
<jsp:include page="modal.jsp"></jsp:include>
