package connection;

public class ExistingAccountException extends Exception{
	
	public ExistingAccountException()
	{
		super("La informaci�n de cuenta proporcionada ya ha sido registrada");
	}

}
