<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Graphique de Bénéfices</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <form method="GET" id="absenceForm">
        date plus tot:<input type="date" id="earlier_date" name="earlier_date" >
        date plus tard:<input type="date" id="lateer_date" name="lateer_date" >
    <input type="submit" value="Envoyer">
</form>

    <h1>Suivi des absences</h1>
    <canvas id="myChart"></canvas>
    <script type="text/javascript">
        let myChart = null;
        document.getElementById('absenceForm').addEventListener('submit', function(event) {
            event.preventDefault();
            const xhr = new XMLHttpRequest();
            xhr.open('POST', 'http://localhost:8080/recycoil-master/statAbs', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onload = function() {
                if (xhr.status === 200) {
                    const absences = JSON.parse(xhr.responseText);
                    myChart = updateChart(absences, myChart);
                } else {
                    console.error('Erreur lors de la récupération des données de bénéfice');
                }
            };
            
            const formData = new FormData(document.getElementById('absenceForm'));
            console.log(formData);
            xhr.send(new URLSearchParams(formData));
        });
        function updateChart(absences, myChart) {
            let labels=[];
            absences.forEach(absence=>{
                 labels.push(absence.annee+"-"+absence.mois);   
            })
            let data =[];
            absences.forEach(absence=>{
                 data.push(absence.nbAbsents);   
            })
           
            
            const ctx = document.getElementById('myChart').getContext('2d');
            if (myChart) {
                myChart.data.labels = labels;
                myChart.data.datasets[0].data = data;
                myChart.update();
            } else {
                myChart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: 'absence',
                            data: data,
                            fill: true,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgb(75, 192, 192)',
                            tension: 0.1
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            }
            return myChart;
        }
    </script>
</body>
</html>
<style type="text/css">
    canvas{
        width: 400px;
        height: 500px;
    }
</style>