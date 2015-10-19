package general;

import java.io.Serializable;

public class Position implements Serializable
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//3 Dimension
	int _x,_y,_z;

/**
 * Instantiates a new position.
 *
 * @param x the x
 * @param y the y
 * @param z the z
 */
//Constructors 
public Position(int x, int y, int z)  
	{
		this._x = x;
		this._y = y;
		this._z = z;
	}

public Position(String pstr) 
{
	pstr = pstr.replaceAll("[{}]","");
	String val[] = pstr.split(",");
	this._x = Integer.parseInt(val[0]);
	this._y = Integer.parseInt(val[1]);
	this._z = Integer.parseInt(val[2]);
}

public Position(Position p) {

	this._x = p._x;
	this._y = p._y;
	this._z = p._z;
}

//Setters & Getters
public int getX() {
	return _x;
}

public void setX(int x) {
	this._x = x;
}

public int getY() {
	return _y;
}

public void setY(int y) {
	this._y = y;
}

public int getZ() {
	return _z;
}

public void setZ(int z) {
	this._z = z;
}

//Valuable function


@Override

public String toString() 
	{
		return "{" + _x + "," + _y + "," + _z + "}";
	}
@Override
public boolean equals(Object obj) 
	{
		Position other = (Position) obj;
		if (_x != other._x)
			return false;
		if (_y != other._y)
			return false;
		if (_z != other._z)
			return false;
		return true;
	}

public int hashCode() 
{
    return  this.toString().hashCode();
}


}
