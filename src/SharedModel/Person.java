package SharedModel;

import java.io.Serializable;

public class Person implements Serializable
{
	private String name;
	private String coinbaseEmail;
	private String btcWalletAddress;

	public Person(String name, String coinbaseEmail, String btcWalletAddress)
	{
		this.name = name;
		this.coinbaseEmail = coinbaseEmail;
		this.btcWalletAddress = btcWalletAddress;
	}
}
