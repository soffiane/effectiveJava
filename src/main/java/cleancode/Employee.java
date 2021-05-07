package cleancode;

import java.math.BigDecimal;
/*
ecrire des switch courts, pour cela, utiliser le pattern Abstract Factory
switch OK sur des objets polymorphes
 */
public abstract class Employee {
	public abstract boolean isPayDay();

	public abstract BigDecimal calculatePay();

	public abstract void deliverPay(BigDecimal pay);

	//Cette fonction ne respecte pas le principe OCP car doit etre modifiée à l'ajout de nouveaux type
	// ni le SRP car elle fait plusieurs choses
	//d'autres fonctions potentielles a créer auront la meme structure. ex: deliverPay(Employee e, BigDecimal pay)
	/*public BigDecimal calculatePay(Employee e)
			throws InvalidEmployeeType {
		switch (e.type) {
			case COMMISSIONED:
				return calculateCommissionedPay(e);
			case HOURLY:
				return calculateHourlyPay(e);
			case SALARIED:
				return calculateSalariedPay(e);
			default:
				throw new InvalidEmployeeType(e.type);
		}*/
}
	interface EmployeeFactory {
		public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType;
	}

	class EmployeeFactoryImpl implements EmployeeFactory {
	@Override
	public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType {
			switch (r.type) {
				case COMMISSIONED:
					return new CommissionedEmployee(r) ;
				case HOURLY:
					return new HourlyEmployee(r);
				case SALARIED:
					return new SalariedEmploye(r);
				default:
					throw new InvalidEmployeeType(r.type);
			}
		}
	}

