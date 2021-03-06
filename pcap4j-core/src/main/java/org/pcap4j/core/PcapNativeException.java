/*_##########################################################################
  _##
  _##  Copyright (C) 2011  Kaito Yamada
  _##
  _##########################################################################
*/

package org.pcap4j.core;

/**
 * @author Kaito Yamada
 * @since pcap4j 0.9.1
 */
public final class PcapNativeException extends Exception {

  private static final long serialVersionUID = -6458526492950674556L;

  private Integer returnCode = null;

  /**
   *
   */
  public PcapNativeException() {
    super();
  }

  /**
   *
   * @param message
   */
  public PcapNativeException(String message){
    super(message);
  }

  /**
   *
   * @param message
   * @param returnCode
   */
  public PcapNativeException(String message, Integer returnCode){
    super(message);
    this.returnCode = returnCode;
  }

  /**
   *
   * @param message
   * @param cause
   */
  public PcapNativeException(String message, Throwable cause){
      super(message, cause);
  }

  /**
   *
   * @param cause
   */
  public PcapNativeException(Throwable cause){
      super(cause);
  }

  /**
   *
   * @return the return code if the native function which caused
   *         this exception returned it; otherwise null.
   */
  public Integer getReturnCode() {
    return returnCode;
  }

  void setReturnCode(Integer returnCode) {
    this.returnCode = returnCode;
  }

}
