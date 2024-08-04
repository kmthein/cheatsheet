<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function loadSections(sectionId) {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "getSubsections?sectionId=" + sectionId, true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var subsections = JSON.parse(xhr.responseText);
				var subsectionSelect = document.getElementById("subsection");
				subsectionSelect.innerHTML = "";

				subsections.forEach(function(subsection) {
					var option = document.createElement("option");
					option.value = subsection.id;
					option.text = subsection.name;
					subsectionSelect.appendChild(option);
				});
			}
		};
		xhr.send();
	}
</script>
<form action="add-cheatsheet" method="post"
	class="container mx-auto row g-3">
	<h2 class="center-title">Create New Cheatsheet</h2>
	<div class="col-12">
		<label for="name" class="form-label">Name</label> <input type="text"
			class="form-control" name="name" id="name">
	</div>
	<div class="col-12">
		<label for="description" class="form-label">Description</label> <input
			type="text" class="form-control" name="description" id="description">
	</div>
	<div class="col-md-6">
		<label for="color" class="form-label">Color</label> <input type="text"
			class="form-control" name="color" id="color">
	</div>
	<div class="col-md-6">
		<label for="language" class="form-label">Language</label> <input
			type="text" id="language" name="language" class="form-control">
	</div>
	<div class="col-md-6">
		<label for="style" class="form-label">Style</label> <select id="style"
			class="form-select" name="style">
			<option disabled selected>Select style</option>
			<option value="old">Original 2011</option>
			<option value="new">Modern 2020</option>
		</select>
	</div>
	<div class="col-md-6">
		<label for="type" class="form-label">Type</label> <select id="type"
			name="type" class="form-select">
			<option disabled selected>Select type</option>
			<option value="cheatsheet">Cheatsheet</option>
			<option value="shortcuts">Keyboard Shortcuts</option>
		</select>
	</div>
	<div class="col-6">
		<label for="section" class="form-label">Section</label> <select
			id="section" name="section" onchange="loadSections(this.value)"
			class="form-select">
			<option disabled selected>Select section</option>
			<c:forEach var="section" items="${sections}">
				<option value="${section.id}">${section.name}</option>
			</c:forEach>
		</select>
	</div>
	<div class="col-6">
		<label for="subsection" class="form-label">Subsection</label> <select
			id="subsection" name="subsection" required class="form-select">
			<option value="">Please select section first</option>
		</select>
	</div>
	<div class="col-12">
		<label for="layout" class="form-label">Layout</label> <select
			id="layout" name="layout" class="form-select">
			<option value="2" selected>two columns</option>
		</select>
	</div>
	<div class="col-12">
		<div class="d-flex justify-content-between">
			<label for="inputAddress" class="form-label">Content</label>
			<div onclick="addNewBlock()"
				style="margin-top: 2px; cursor: pointer;">
				<i class="bi bi-arrow-down-square"></i> <label
					class="form-check-label ml-2">Add New Block</label>
			</div>
		</div>
		<div class="col-12">
			<div id="block-container">
				<!-- The initial block is hidden and used only as a template -->
				<div class="row g-3 block d-none" id="template-block">
					<div class="col-12 mb-2">
						<label for="title1" class="form-label">Title</label> <input
							type="text" id="title1" class="form-control" name="title1">
					</div>
					<div class="d-flex">
						<div class="col-md-6">
							<label for="column1" class="form-label">Column 1</label>
						</div>
						<div class="col-md-6">
							<label for="column2" class="form-label">Column 2</label>
						</div>
					</div>
					<div class="input-container">
						<div class="d-flex gap-2 mb-4">
							<div class="col-md-6">
								<input type="text" class="form-control" name="b1column1"
									id="column1">
							</div>
							<div class="col-md-6">
								<input type="text" class="form-control" name="b1column2"
									id="column2">
							</div>
						</div>
					</div>
					<div class="d-flex justify-content-end">
						<div class="row-add-btn" style="margin-top: 2px; cursor: pointer;">
							<i class="bi bi-plus-circle"></i> <label
								class="form-check-label ml-2">Add New Row</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-12 d-flex justify-content-center my-4">
		<button type="submit" class="btn btn-secondary">Submit</button>
	</div>
</form>
<script>
let blockCount = 1;

function addNewBlock() {
    const blockContainer = document.getElementById("block-container");
    const templateBlock = document.getElementById("template-block");

    // Clone the template block
    const newBlock = templateBlock.cloneNode(true);

    // Remove the 'd-none' class to make it visible
    newBlock.classList.remove("d-none");

    // Increment blockCount to ensure unique naming
    blockCount++;
    
    // Update the IDs and names of the new block's elements
    const titleInput = newBlock.querySelector('input[name="title1"]');
    titleInput.name = `b${blockCount}title`;
    titleInput.id = `title${blockCount}`;
    
    const inputContainer = newBlock.querySelector('.input-container');
    inputContainer.querySelectorAll('input').forEach((input, index) => {
        input.name = `b${blockCount}column${index + 1}`;
    });

    // Append the new block to the container
    blockContainer.appendChild(newBlock);

    // Add event listener for the "Add New Row" button within the new block
    const rowAddBtn = newBlock.querySelector('.row-add-btn');
    rowAddBtn.addEventListener('click', function(event) {
        event.preventDefault();
        addNewInputFields(inputContainer);
    });
}

function addNewInputFields(container) {
    const inputCount = container.getElementsByTagName("input").length;
    const newFieldSet = document.createElement('div');
    newFieldSet.className = 'd-flex gap-2 mb-4';

    const input1 = document.createElement("input");
    input1.type = "text";
    input1.name = `b${blockCount}column${inputCount + 1}`;
    input1.className = "form-control";

    const input2 = document.createElement("input");
    input2.type = "text";
    input2.name = `b${blockCount}column${inputCount + 2}`;
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

document.addEventListener('DOMContentLoaded', function() {
    // Initial "Add New Row" button in the template block
    const initialInputContainer = document.querySelector('#template-block .input-container');
    document.querySelector('#template-block .row-add-btn').addEventListener('click', function(event) {
        event.preventDefault();
        addNewInputFields(initialInputContainer);
    });
});
</script>