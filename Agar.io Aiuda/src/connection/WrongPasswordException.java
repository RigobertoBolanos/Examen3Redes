package connection;

public class WrongPasswordException extends Exception{

	public WrongPasswordException()
	{
		super("La contrase�a es incorrecta");
	}
}
