package com.simo.ugmate.tools;

public class CircularBuf {
	private int mStart;
	private int mLen;
	private byte[] mBuffer;
	
	public CircularBuf(int capacity) {
		mStart = 0;
		mLen = 0;
		mBuffer = new byte[capacity];
	}
	
	public void Reset() {
		mStart = 0;
		mLen = 0;
	}
	
	public int Length() {
		return mLen;
	}
	
	public int Capacity() {
		return mBuffer.length;
	}
	
	public int ReadPos() {
		return mStart;
	}
	
	public byte[] RawData() {
		return mBuffer;
	}

	public boolean AdvanceReadPos(int count) {
		if (count >= mLen) {
			Reset();
			return false;
		}
		
		mStart += count;
		if (mStart >= mBuffer.length) {
			mStart -= mBuffer.length;
		}
		
		mLen -= count;
		return true;
	}
	
	public boolean AdvanceWritePos(int count) {
		if (count + mLen > mBuffer.length) {
			return false;
		}

		mLen += count;
		return true;
	}
	
	private void GetReadRegions(StackInteger regIdx1, StackInteger regLen1,
			StackInteger regIdx2, StackInteger regLen2) {
		int idx1 = 0, len1 = 0, idx2 = 0, len2 = 0;
		
		idx1 = mStart;
		len1 = mLen;
		if (idx1 + len1 > mBuffer.length) {
			len1 = mBuffer.length - idx1;
			idx2 = 0;
			len2 = mLen - len1;
		} else {
			idx2 = 0;
			len2 = 0;
		}
		
		regIdx1.setValue(idx1);
		regLen1.setValue(len1);
		regIdx2.setValue(idx2);
		regLen2.setValue(len2);
	}

	private void GetWriteRegions(StackInteger regIdx1, StackInteger regLen1,
			StackInteger regIdx2, StackInteger regLen2) {
		int idx1 = 0, len1 = 0, idx2 = 0, len2 = 0;
		
		idx1 = mStart + mLen;
		if (idx1 >= mBuffer.length) {
			idx1 -= mBuffer.length;
		}
		len1 = mBuffer.length - mLen;
		
		if (idx1 + len1 > mBuffer.length) {
			len1 = mBuffer.length - idx1;
			idx2 = 0;
			len2 = mStart;
		} else {
			idx2 = 0;
			len2 = 0;
		}
		
		regIdx1.setValue(idx1);
		regLen1.setValue(len1);
		regIdx2.setValue(idx2);
		regLen2.setValue(len2);
	}
	
	/**
	 * 读取数据。
	 * @return 读取到的数据。
	 */
	public byte[] ReadData() 
	{
		return ReadData(mLen); //读取指定长度的数据。
	} //public byte[] ReadData()
	
	/**
	 * 读取指定长度的数据。
	 * @param count 要读取的长度。
	 * @return 读取得到的数据。
	 */
	public byte[] ReadData(int count) 
	{
		StackInteger regIdx1 = new StackInteger(0); 
		StackInteger regLen1 = new StackInteger(0);
		StackInteger regIdx2 = new StackInteger(0);
		StackInteger regLen2 = new StackInteger(0);

		int cnt = count;
		if (count > mLen) {
			cnt = mLen;
		}
		GetReadRegions(regIdx1, regLen1, regIdx2, regLen2);
		
		byte[] data = new byte[cnt];
		if (regLen1.getValue() >= cnt) {
			System.arraycopy(mBuffer, regIdx1.getValue(),
					data, 0, cnt);
		} else {
			System.arraycopy(mBuffer, regIdx1.getValue(),
					data, 0, regLen1.getValue());
			System.arraycopy(mBuffer, regIdx2.getValue(),
					data, regLen1.getValue(), cnt - regLen1.getValue());
		}
		
		return data;
	} //public byte[] ReadData(int count)
	
	public boolean WriteData(byte[] data) {
		StackInteger regIdx1 = new StackInteger(0); 
		StackInteger regLen1 = new StackInteger(0);
		StackInteger regIdx2 = new StackInteger(0);
		StackInteger regLen2 = new StackInteger(0);
		
		if (data.length > mBuffer.length - mLen) {
			return false;
		}
		
		this.GetWriteRegions(regIdx1, regLen1, regIdx2, regLen2);
		
		if (regLen1.getValue() >= data.length) {
			System.arraycopy(data, 0,
					mBuffer, regIdx1.getValue(), data.length);
		} else {
			System.arraycopy(data, 0,
					mBuffer, regIdx1.getValue(), regLen1.getValue());
			System.arraycopy(data, regLen1.getValue(),
					mBuffer, regIdx2.getValue(), data.length - regLen1.getValue());
		}

		return AdvanceWritePos(data.length);
	}
	
	public boolean isReadTwoRegion() {
		StackInteger regIdx1 = new StackInteger(0); 
		StackInteger regLen1 = new StackInteger(0);
		StackInteger regIdx2 = new StackInteger(0);
		StackInteger regLen2 = new StackInteger(0);

		GetReadRegions(regIdx1, regLen1, regIdx2, regLen2);
		
		return (regLen2.getValue() != 0);
	}
}
