let loadStatisticBtn = document.getElementById('loadStatistic')

loadStatisticBtn.addEventListener('click', onLoadStatistic);

function onLoadStatistic(event) {
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    let authorsContainer = document.getElementById('authors-container')
    authorsContainer.innerHTML = ''

    fetch("http://localhost:8080/api/statistic/", requestOptions)
        .then(response => response.json())
        .then(json => json.forEach(statistic => {
            // here we will create some elements and add them to the table.
            let row = document.createElement('tr')

            let idCol = document.createElement('td')
            let ip_addressCol = document.createElement('td')
            let local_date_timeCol = document.createElement('td')
            let actionCol = document.createElement('td')

            idCol.textContent = statistic.id
            ip_addressCol.textContent = statistic.ipAddress
            local_date_timeCol.textContent = statistic.localDateTime

            // add the columns to the parent row
            row.appendChild(idCol)
            row.appendChild(ip_addressCol)
            row.appendChild(local_date_timeCol)
            row.appendChild(actionCol)

            authorsContainer.appendChild(row);
        }))
        .catch(error => console.log('error', error));
}