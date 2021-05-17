package cleancode;

import java.math.BigDecimal;

public class SalariedEmploye extends Employee {
	public SalariedEmploye(EmployeeRecord r) {
		super();
	}

	@Override
	public boolean isPayDay() {
		return false;
	}

	@Override
	public BigDecimal calculatePay() {
		return null;
	}

	@Override
	public void deliverPay(BigDecimal pay) {

	}
}
