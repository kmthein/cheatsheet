<form action="add-cheatsheet" method="post" class="container mx-auto row g-3">
	<h2 class="center-title">Create New Cheatsheet</h2>
	<div class="col-12">
		<label for="description" class="form-label">Description</label> <input
			type="text" class="form-control" name="description" id="description">
	</div>
	<div class="col-md-6">
		<label for="color" class="form-label">Color</label> <input
			type="email" class="form-control" name="color" id="color">
	</div>
	<div class="col-md-6">
		<label for="languauge" class="form-label">Language</label> <input
			type="text" id="languauge" name="language" class="form-control">
	</div>
	<div class="col-md-6">
		<label for="style" class="form-label">Style</label> <select
			id="style" class="form-select" name="style">
			<option selected>Choose...</option>
			<option>...</option>
		</select>
	</div>
	<div class="col-md-6">
		<label for="type" class="form-label">Type</label> <select
			id="type" name="type" class="form-select">
			<option selected>Choose...</option>
			<option>...</option>
		</select>
	</div>
	<div class="col-12">
		<label for="section" class="form-label">Section</label> <select
			id="section" name="section" class="form-select">
			<option selected>Choose...</option>
			<option>...</option>
		</select>
	</div>
	<div class="col-12">
		<label for="subsection" class="form-label">Subsection</label> <select
			id=""subsection" name="subsection" class="form-select">
			<option selected>Choose...</option>
			<option>...</option>
		</select>
	</div>
	<div class="col-12">
		<label for="subsection" class="form-label">Layout</label> <select
			id=""subsection" name="subsection" class="form-select">
			<option value="2" selected>two columns</option>
<!-- 			<option value="3">three columns</option>
			<option value="4">four columns</option> -->
		</select>
	</div>
	<div class="col-12">
		<label for="inputAddress" class="form-label">Add Your Content</label> 
	<div class="col-12">
		<!-- <div type="button" style="display: inline-block"  data-bs-toggle="modal"
			data-bs-target="#exampleModal">
			<i class="bi bi-plus-square-fill"></i> <label
				class="form-check-label ml-2" for="gridCheck" style="cursor: pointer">Add New Block </label>
		</div> -->
		<div class="row g-3" id="cheatsheetForm">
					<div class="col-12 mb-2">
						<label for="title" class="form-label">Title</label> <input
							type="text" id="title" class="form-control" name="title">
					</div>
					<div class="d-flex">
					<div class="col-md-6">
						<label for="column1" class="form-label" name="column1">Column
							1</label>
					</div>
					<div class="col-md-6">
						<label for="column2" class="form-label" name="column2">Column
							2</label>
					</div>
					</div>
					<div id="input-container">
						<div class="d-flex gap-2 mb-4">
							<div class="col-md-6">
								<input type="text" class="form-control" name="column1" id="column1">
							</div>
							<div class="col-md-6">
								<input type="text" class="form-control" name="column2" id="column2">
							</div>
						</div>
					</div>
				<!-- 				<button id="row-add"
						style="border: 1px solid #606060; background: #606060; color: #FFF; font-size: 16px; margin-top: 2px; margin-bottom: 10px">Add
						New Row</button>
 -->
				<div id="row-add" style="margin-top: 2px;">
					<i class="bi bi-plus-square-fill"></i> <label
				class="form-check-label ml-2" for="gridCheck" style="cursor: pointer">Add New Row</label>
					</div>
				</form>
	</div>
	<div class="col-12 d-flex justify-content-center my-4">
    <button type="submit" class="btn btn-secondary">Submit</button>
  </div>
</form>
<jsp:include page="modal.jsp"></jsp:include>
<script>
        const addRowBtn = document.getElementById('row-add');
        addRowBtn.addEventListener('click', (event) => {
            event.preventDefault();
            addNewInputFields();
        });
        function addNewInputFields() {
        	const container = document.getElementById("input-container");
        	var inputCount = container.getElementsByTagName("input").length;
            const newFieldSet = document.createElement('div');
    		newFieldSet.className = 'd-flex gap-2 mb-4';
    		const input1 = document.createElement("input");
    		input1.type = "text";
    		input1.name = "column" + (inputCount + 1);
    		input1.className = "form-control";
    		const input2 = document.createElement("input");
    		input2.type = "text";
    		input2.name = "column" + (inputCount + 2);
    		input2.className = "form-control";
    		const div1 = document.createElement("div");
            div1.className = "col-md-6";
            div1.appendChild(input1);

            const div2 = document.createElement("div");
            div2.className = "col-md-6";
            div2.appendChild(input2);

            newFieldSet.appendChild(div1);
            newFieldSet.appendChild(div2);

            container.appendChild(newFieldSet);
        }
        
        const form = document.getElementById('cheatsheetForm');
        
        const submitBtn = document.getElementById('submit-btn');
        submitBtn.addEventListener('click', (event) => {
        	event.preventDefault();
        	console.log("Submit");
        	const formData = new FormData(form);
        	const data = {};
            formData.forEach((value, key) => {
                if (!data[key]) {
                    data[key] = value;
                } else {
                    if (!Array.isArray(data[key])) {
                        data[key] = [data[key]];
                    }
                    data[key].push(value);
                }
            });
            console.log(data);
        })
</script>
