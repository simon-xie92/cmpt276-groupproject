let calculateBtn = document.getElementById("calculate-btn");
let result = document.getElementById("result");
let calculate = () => {
    const stockPrice = parseFloat(document.getElementById('stockPrice').value);
    const numShares = parseInt(document.getElementById('numShares').value);
    
    /*
    // Validate inputs
    if (isNaN(stockPrice) || isNaN(numShares) || stockPrice <= 0 || numShares <= 0) {
        document.getElementById('result').textContent = "Please enter valid positive numbers.";
        return;
    }
    */
    // Calculate volume
    const volume = stockPrice * numShares;
    console.log(volume);
    // Display the result
    result.innerHTML = `Total Traded Value: $${volume.toFixed(2)}`;
    
};
calculateBtn.addEventListener("click", calculate);
window.addEventListener("load", calculate);

