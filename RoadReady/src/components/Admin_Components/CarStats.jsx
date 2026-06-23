
import React, { useState, useEffect } from 'react';
import { Chart } from 'primereact/chart';
import axios from 'axios';

const  CarStats = () =>{

            const bookingStatsApi = "http://localhost:8080/api/admin/getCarStats"

    const [chartData, setChartData] = useState({});
    const [chartOptions, setChartOptions] = useState({});

    useEffect(() => {
        const getData= async() =>{ try{
                const response = await axios.get(bookingStatsApi)
                const pdata = {
            labels: response.data.label,
            datasets: [
                {
                    label: "Car Status",
                    data: response.data.data,
                    backgroundColor: [
                        'rgba(255, 159, 64, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(153, 102, 255, 0.2)'
                      ],
                      borderColor: [
                        'rgb(255, 159, 64)',
                        'rgb(75, 192, 192)',
                        'rgb(54, 162, 235)',
                        'rgb(153, 102, 255)'
                      ],
                      borderWidth: 1
                }
            ]
        };
        const options = {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        };

        setChartData(pdata);
        setChartOptions(options);


            }
            catch(err){
                console.log(err?.response)
            }
            
        }
                getData()

        },[])

    return (
            <Chart type="pie" data={chartData} options={chartOptions} className="w-full md:w-30rem" />
        
    )
}
        
export default CarStats