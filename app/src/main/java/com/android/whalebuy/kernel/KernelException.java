package com.android.whalebuy.kernel;

public class KernelException extends Exception
{

  /**
   * 序列化ID
   */
  private static final long serialVersionUID = -1746394987507445017L;
  
  private int mErrId = 0; //错误ID
  
  public KernelException(final int nErrId, final String szExp)
  {
    super(szExp);
    mErrId = nErrId;    
  }
  
  public int getErrCode()
  { return mErrId; }
}
