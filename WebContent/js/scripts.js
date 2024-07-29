/*!
* Start Bootstrap - Clean Blog v6.0.9 (https://startbootstrap.com/theme/clean-blog)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-clean-blog/blob/master/LICENSE)
*/
window.addEventListener('DOMContentLoaded', () => {
    let scrollPos = 0;
    const mainNav = document.getElementById('mainNav');
    const headerHeight = mainNav.clientHeight;
    window.addEventListener('scroll', function() {
        const currentTop = document.body.getBoundingClientRect().top * -1;
        if ( currentTop < scrollPos) {
            // Scrolling Up
            if (currentTop > 0 && mainNav.classList.contains('is-fixed')) {
                mainNav.classList.add('is-visible');
            } else {
                console.log(123);
                mainNav.classList.remove('is-visible', 'is-fixed');
            }
        } else {
            // Scrolling Down
            mainNav.classList.remove(['is-visible']);
            if (currentTop > headerHeight && !mainNav.classList.contains('is-fixed')) {
                mainNav.classList.add('is-fixed');
            }
        }
        scrollPos = currentTop;
    });
})


document.addEventListener('DOMContentLoaded', (event) => {
    const column2Input = document.getElementById('column2');
    const additionalFields = document.getElementById('additionalFields');

    column2Input.addEventListener('focusout', (event) => {
        if (column2Input.value.trim() !== '') {
            addNewInputFields();
        }
    });

    function addNewInputFields() {
        const newFieldSet = document.createElement('div');
        newFieldSet.innerHTML = `
            <div class="col-md-6">
                <input type="text" class="form-control" placeholder="Column 1">
            </div>
            <div class="col-md-6">
                <input type="text" class="form-control" placeholder="Column 2">
            </div>
        `;
        additionalFields.appendChild(newFieldSet);

        // Optional: Add an event listener to the new column2 input to continue adding new fields
        const newColumn2Input = newFieldSet.querySelector('.col-md-6:nth-child(2) input');
        newColumn2Input.addEventListener('input', (event) => {
            if (newColumn2Input.value.trim() !== '') {
                addNewInputFields();
            }
        });
    }
});