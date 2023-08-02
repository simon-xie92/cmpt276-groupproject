const formatter = new Intl.NumberFormat("en-US", {
    style: "currency",
    currency: "USD"
  });
  
  const form = document.querySelector(".form");
  const paymentAmount = document.querySelector(".payment-amount");
  
  // principle amount borrowed
  const principle = () =>
    parseInt(document.getElementById("principle").value, 10);
  
  // monthly interest rate
  const monthlyInterestRate = () => {
    const rate = parseInt(document.getElementById("interest-rate").value, 10);
    return rate / 100 / 12;
  };
  
  // number of payments months
  const numOfPaymentMonths = () => {
    const loanTerm = parseInt(document.getElementById("loan-term").value, 0);
    return loanTerm * 12;
  };
  
  // (p * r * (1 + r)^n) / ((1 + r)^n - 1)
  function monthlyPayment(principle, numOfPayments, interestRate) {
    if (interestRate === 0) {
      return principle / numOfPayments;
    }
  
    return (
      (principle * interestRate * Math.pow(1 + interestRate, numOfPayments)) /
      (Math.pow(1 + interestRate, numOfPayments) - 1)
    );
  }
  
  const submit = (e) => {
    e.preventDefault();
  
    const monthlyMortgagePayment = monthlyPayment(
      principle(),
      numOfPaymentMonths(),
      monthlyInterestRate()
    ).toFixed(2);
  
    const formattedPayment = formatter.format(monthlyMortgagePayment);
  
    paymentAmount.textContent = `${formattedPayment}`;
    paymentAmount.style.visibility = "visible";
  };
  
  form.onsubmit = submit;
  