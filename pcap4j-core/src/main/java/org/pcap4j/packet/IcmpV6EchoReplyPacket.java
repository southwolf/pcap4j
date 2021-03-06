/*_##########################################################################
  _##
  _##  Copyright (C) 2013-2014  Kaito Yamada
  _##
  _##########################################################################
*/

package org.pcap4j.packet;

import org.pcap4j.packet.factory.PacketFactories;
import org.pcap4j.packet.namednumber.NotApplicable;
import org.pcap4j.util.ByteArrays;

/**
 * @author Kaito Yamada
 * @since pcap4j 0.9.15
 */
public final class IcmpV6EchoReplyPacket extends IcmpIdentifiablePacket {

  /**
   *
   */
  private static final long serialVersionUID = -3713149118572340972L;

  private final IcmpV6EchoReplyHeader header;
  private final Packet payload;

  /**
   * A static factory method.
   * This method validates the arguments by {@link ByteArrays#validateBounds(byte[], int, int)},
   * which may throw exceptions undocumented here.
   *
   * @param rawData
   * @param offset
   * @param length
   * @return a new IcmpV6EchoReplyPacket object.
   * @throws IllegalRawDataException
   */
  public static IcmpV6EchoReplyPacket newPacket(
    byte[] rawData, int offset, int length
  ) throws IllegalRawDataException {
    ByteArrays.validateBounds(rawData, offset, length);
    return new IcmpV6EchoReplyPacket(rawData, offset, length);
  }

  private IcmpV6EchoReplyPacket(
    byte[] rawData, int offset, int length
  ) throws IllegalRawDataException {
    this.header = new IcmpV6EchoReplyHeader(rawData, offset, length);

    int payloadLength = length - header.length();
    if (payloadLength > 0) {
      this.payload
        = PacketFactories.getFactory(Packet.class, NotApplicable.class)
            .newInstance(rawData, offset + header.length(), payloadLength, NotApplicable.UNKNOWN);
    }
    else {
      this.payload = null;
    }
  }

  private IcmpV6EchoReplyPacket(Builder builder) {
    super(builder);
    this.payload = builder.payloadBuilder != null ? builder.payloadBuilder.build() : null;
    this.header = new IcmpV6EchoReplyHeader(builder);
  }

  @Override
  public IcmpV6EchoReplyHeader getHeader() {
    return header;
  }

  @Override
  public Packet getPayload() {
    return payload;
  }

  @Override
  public Builder getBuilder() {
    return new Builder(this);
  }

  /**
   * @author Kaito Yamada
   * @since pcap4j 0.9.15
   */
  public static
  final class Builder extends org.pcap4j.packet.IcmpIdentifiablePacket.Builder {

    private Packet.Builder payloadBuilder;

    /**
     *
     */
    public Builder() {}

    private Builder(IcmpV6EchoReplyPacket packet) {
      super(packet);
      this.payloadBuilder = packet.payload != null ? packet.payload.getBuilder() : null;
    }

    @Override
    public Builder identifier(short identifier) {
      super.identifier(identifier);
      return this;
    }

    @Override
    public Builder sequenceNumber(short sequenceNumber) {
      super.sequenceNumber(sequenceNumber);
      return this;
    }

    @Override
    public Builder payloadBuilder(Packet.Builder payloadBuilder) {
      this.payloadBuilder = payloadBuilder;
      return this;
    }

    @Override
    public Packet.Builder getPayloadBuilder() {
      return payloadBuilder;
    }

    @Override
    public IcmpV6EchoReplyPacket build() {
      return new IcmpV6EchoReplyPacket(this);
    }

  }

  /**
   * @author Kaito Yamada
   * @since pcap4j 0.9.15
   */
  public static
  final class IcmpV6EchoReplyHeader extends IcmpIdentifiableHeader {

    /*
     *  0                            15
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |         Identifier            |
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |       Sequence Number         |
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     */

    /**
     *
     */
    private static final long serialVersionUID = -5140524898291969262L;

    private IcmpV6EchoReplyHeader(
      byte[] rawData, int offset, int length
    ) throws IllegalRawDataException {
      super(rawData, offset, length);
    }

    private IcmpV6EchoReplyHeader(Builder builder) { super(builder); }

    @Override
    protected String getHeaderName() {
      return "ICMPv6 Echo Reply Header";
    }

  }

}
