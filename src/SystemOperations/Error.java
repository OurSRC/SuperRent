package SystemOperations;

public class Error {
	static public final int	INT_UNKNOWN = -1;
	static public final long	LNG_UNKNOWN = -1;
	static public final float	FLT_UNKNOWN = -1;
	static public final double	DBL_UNKNOWN = -1;
	static public final byte	BYT_UNKNOWN = -1;
	static public final short	SHT_UNKNOWN = -1;
	static public final String	STR_UNKNOWN = null;
	static public final Object	OBJ_UNKNOWN = null;
	
	static public final int	ERROR_GENERAL = -1;
	static public final int	ERROR_RECORD_DUPLICATE = -2;
	static public final int	ERROR_WRONG_PASSWORD = -3;
	static public final int	ERROR_USERNAME_ALREADY_EXIT = -4;

	static private int lastError;
	static public void setLastError(int err){
		lastError = err;
	}
	static public int getLastError(){
		return lastError;
	}
	static public String translateError(int err){
		switch(err){
		case ERROR_GENERAL:
			return "General error";
		case ERROR_RECORD_DUPLICATE:
			return "Record duplicate";
		case ERROR_WRONG_PASSWORD:
			return "Wrong Password";
		case ERROR_USERNAME_ALREADY_EXIT:
			return "Username alread exists";
		}
		return null;
	}
}
