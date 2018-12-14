package SharedModel;

import java.io.Serializable;

public class Owner implements Serializable
{
	private String name;
	private String coinbaseEmail;
	private String btcWalletAddress;

	public Owner(String name, String coinbaseEmail, String btcWalletAddress)
	{
		this.name = name;
		this.coinbaseEmail = coinbaseEmail;
		this.btcWalletAddress = btcWalletAddress;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCoinbaseEmail()
	{
		return coinbaseEmail;
	}

	public void setCoinbaseEmail(String coinbaseEmail)
	{
		this.coinbaseEmail = coinbaseEmail;
	}

	public String getBtcWalletAddress()
	{
		return btcWalletAddress;
	}

	public void setBtcWalletAddress(String btcWalletAddress)
	{
		this.btcWalletAddress = btcWalletAddress;
	}

	@Override
	public String toString()
	{
		return name + "\t\t" + coinbaseEmail + "\t\t" + btcWalletAddress;
	}
}
