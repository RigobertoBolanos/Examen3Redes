package connection;

public class AccountNotFoundException extends Exception{

	public AccountNotFoundException()
	{
		super("No se encontr� ninguna cuenta de usuario asociada al correo especificado");
	}
}
