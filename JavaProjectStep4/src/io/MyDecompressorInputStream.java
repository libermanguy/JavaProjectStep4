package io;

import java.io.IOException;
import java.io.InputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class MyDecompressorInputStream.
 */
public class MyDecompressorInputStream extends InputStream {

	
	/** The _in. */
	InputStream _in;
	
	/** The _in byte. */
	int _inByte;
	
	/** The _in count. */
	int _inCount;
	
	/** The _opened. */
	static boolean _opened = false;
	
	/**
	 * Instantiates a new my decompressor input stream.
	 *
	 * @param in the in
	 */
	public MyDecompressorInputStream(InputStream in) {
		super();
		_in = in;
		_inCount = 0;
	}


	/* (non-Javadoc)
	 * @see java.io.InputStream#read()
	 */
	@Override
	public int read() throws IOException {
		
		if (!_opened)
		{
		//	System.out.println("Now Open");
			_inByte =_in.read();
			_inCount=_in.read();
		//	System.out.print("NewChar " +_inByte);
		//	System.out.println(" Times " +_inCount);
			_inCount--;
			_opened = true;
			return (int) _inByte;
		}
		else
		{
		if (_inCount > 0)
			{

				_inCount--;
				return _inByte;
			}
		else
			{
				_inByte=(byte) _in.read();
	//			System.out.print("NewChar " +_inByte);
				_inCount=(byte) _in.read();
		//		System.out.println(" Times " +_inCount);
				_inCount--;
				return (int) _inByte;
			}
		}
	
	}

}
