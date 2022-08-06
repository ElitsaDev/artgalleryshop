let loadProductsBtn = document.getElementById('loadProducts')

loadProductsBtn.addEventListener('click', onLoadProducts);

function onLoadProducts(event) {
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    let authorsContainer = document.getElementById('authors-container')
    authorsContainer.innerHTML = ''

    fetch("http://localhost:8080/api/products/", requestOptions)
        .then(response => response.json())
        .then(json => json.forEach(product => {
            // here we will create some elements and add them to the table.
            let row = document.createElement('tr')

            let titleCol = document.createElement('td')
            let authorCol = document.createElement('td')
            let isbnCol = document.createElement('td')
            let actionCol = document.createElement('td')

            titleCol.textContent = product.title
            authorCol.textContent = product.author.name
            isbnCol.textContent = product.isbn

            // add the columns to the parent row
            row.appendChild(titleCol)
            row.appendChild(authorCol)
            row.appendChild(isbnCol)
            row.appendChild(actionCol)

            authorsContainer.appendChild(row);
        }))
        .catch(error => console.log('error', error));
}