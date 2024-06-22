<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Graphique de Bénéfice</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <form action="<%= request.getContextPath() %>/BeneficeServlet" method="post" id="beneficeForm">
    <label for="anneeMin">Année minimum :</label>
    <input value="" type="text" id="anneeMin" name="anneeMin"><br><br>
    
    <label for="anneeMax">Année maximum :</label>
    <input value="" type="text" id="anneeMax" name="anneeMax"><br><br>
    
    <label for="moisMin">Mois minimum :</label>
    <input value="" type="text" id="moisMin" name="moisMin"><br><br>
    
    <label for="moisMax">Mois maximum :</label>
    <input value="" type="text" id="moisMax" name="moisMax"><br><br>
    
    <input type="submit" value="Envoyer">
</form>

    <h1>Statistique de benefice</h1>
    <canvas id="myChart"></canvas>

    <script type="text/javascript">
        let myChart = null;
        document.getElementById('beneficeForm').addEventListener('submit', function(event) {
            event.preventDefault();
            const xhr = new XMLHttpRequest();
            xhr.open('POST', 'BeneficeServlet', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onload = function() {
                if (xhr.status === 200) {
                    const benefices = JSON.parse(xhr.responseText);
                    console.log(benefices);
                    myChart = updateChart(benefices, myChart);
                } else {
                    console.error('Erreur lors de la récupération des données de bénéfice');
                }
            };
            
            const formData = new FormData(document.getElementById('beneficeForm'));
            xhr.send(new URLSearchParams(formData));
        });
        function updateChart(benefices, myChart) {
            let labels=[];
            benefices.forEach(benefice=>{
                 labels.push(benefice.annee+"-"+benefice.mois);   
            })
            const data = benefices.map(item => item.benefice);
            
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
                            label: 'Bénéfice',
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