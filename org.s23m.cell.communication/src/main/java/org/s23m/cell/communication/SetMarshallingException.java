package org.s23m.cell.communication;

public class SetMarshallingException extends Exception {

	private static final long serialVersionUID = 1L;
    
	public SetMarshallingException(String message) {
        super(message);
    }
    
	public SetMarshallingException(String message, Throwable cause) {
        super(message, cause);
    }
	
    public SetMarshallingException(Throwable cause) {
        super(cause);
    }
}
