package cleancode;


/**
 * pattern abstract factory
 */
public class EmployeeFactoryImpl implements EmployeeFactory {
	@Override
	public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType {
		switch (r.type) {
			case "COMMISSIONED":
				return new CommissionedEmployee(r) ;
			case "HOURLY":
				return new HourlyEmployee(r);
			case "SALARIED":
				return new SalariedEmploye(r);
			default:
				throw new InvalidEmployeeType(r.type);
		}
	}
}
