package br.ufc.tpii.vmsys.exceptions;

public class NegativeFundsException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegativeFundsException() {
		super();
	}

	public NegativeFundsException(String message) {
		super(message);
	}
	
}