/*
 * TeleStax, Open Source Cloud Communications  Copyright 2012.
 * and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;


import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.Ext2QoSSubscribed;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.Ext2QoSSubscribed_SourceStatisticsDescriptor;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtQoSSubscribed_BitRateExtended;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class Ext2QoSSubscribedImpl extends OctetStringBase implements Ext2QoSSubscribed {

    private static final String DATA = "data";

    private static final String SOURCE_STATISTICS_DESCRIPTOR = "sourceStatisticsDescriptor";
    private static final String OPTIMISED_FOR_SIGNALLING_TRAFFIC = "optimisedForSignallingTraffic";
    private static final String MAX_BIT_RATE_FOR_DOWNLINK_EXTENDED = "maximumBitRateForDownlinkExtended";
    private static final String GUARANTEED_BIT_RATE_FOR_DOWNLINK_EXTENDED = "guaranteedBitRateForDownlinkExtended";

    private static final String DEFAULT_VALUE = null;
    private static final int DEFAULT_INT_VALUE = 0;
    private static final boolean DEFAULT_BOOL_VALUE = false;

    public Ext2QoSSubscribedImpl() {
        super(1, 3, "Ext2QoSSubscribed");
    }

    public Ext2QoSSubscribedImpl(byte[] data) {
        super(1, 3, "Ext2QoSSubscribed", data);
    }

    public Ext2QoSSubscribedImpl(Ext2QoSSubscribed_SourceStatisticsDescriptor sourceStatisticsDescriptor, boolean optimisedForSignallingTraffic,
            ExtQoSSubscribed_BitRateExtended maximumBitRateForDownlinkExtended, ExtQoSSubscribed_BitRateExtended guaranteedBitRateForDownlinkExtended) {
        super(1, 3, "Ext2QoSSubscribed");

        this.setData(sourceStatisticsDescriptor, optimisedForSignallingTraffic, maximumBitRateForDownlinkExtended, guaranteedBitRateForDownlinkExtended);
    }

    protected void setData(Ext2QoSSubscribed_SourceStatisticsDescriptor sourceStatisticsDescriptor, boolean optimisedForSignallingTraffic,
            ExtQoSSubscribed_BitRateExtended maximumBitRateForDownlinkExtended, ExtQoSSubscribed_BitRateExtended guaranteedBitRateForDownlinkExtended) {
        this.data = new byte[3];

        this.data[0] = (byte) ((sourceStatisticsDescriptor != null ? sourceStatisticsDescriptor.getCode() : 0) | ((optimisedForSignallingTraffic ? 1 : 0) << 4));

        this.data[1] = (byte) (maximumBitRateForDownlinkExtended != null ? maximumBitRateForDownlinkExtended.getSourceData() : 0);
        this.data[2] = (byte) (guaranteedBitRateForDownlinkExtended != null ? guaranteedBitRateForDownlinkExtended.getSourceData() : 0);
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public Ext2QoSSubscribed_SourceStatisticsDescriptor getSourceStatisticsDescriptor() {
        if (this.data == null || this.data.length < 1)
            return null;

        return Ext2QoSSubscribed_SourceStatisticsDescriptor.getInstance(this.data[0] & 0x07);
    }

    @Override
    public boolean isOptimisedForSignallingTraffic() {
        if (this.data == null || this.data.length < 1)
            return false;

        if ((this.data[0] & 0x10) != 0)
            return true;
        else
            return false;
    }

    @Override
    public ExtQoSSubscribed_BitRateExtended getMaximumBitRateForDownlinkExtended() {
        if (this.data == null || this.data.length < 2)
            return null;

        return new ExtQoSSubscribed_BitRateExtendedImpl(this.data[1] & 0xFF, true);
    }

    @Override
    public ExtQoSSubscribed_BitRateExtended getGuaranteedBitRateForDownlinkExtended() {
        if (this.data == null || this.data.length < 3)
            return null;

        return new ExtQoSSubscribed_BitRateExtendedImpl(this.data[2] & 0xFF, true);
    }

    @Override
    public String toString() {
        if (this.data != null && this.data.length >= 1) {
            Ext2QoSSubscribed_SourceStatisticsDescriptor sourceStatisticsDescriptor = getSourceStatisticsDescriptor();
            boolean optimisedForSignallingTraffic = isOptimisedForSignallingTraffic();
            ExtQoSSubscribed_BitRateExtended maximumBitRateForDownlinkExtended = getMaximumBitRateForDownlinkExtended();
            ExtQoSSubscribed_BitRateExtended guaranteedBitRateForDownlinkExtended = getGuaranteedBitRateForDownlinkExtended();

            StringBuilder sb = new StringBuilder();
            sb.append(_PrimitiveName);
            sb.append(" [");

            if (sourceStatisticsDescriptor != null) {
                sb.append("sourceStatisticsDescriptor=");
                sb.append(sourceStatisticsDescriptor);
                sb.append(", ");
            }
            sb.append("optimisedForSignallingTraffic=");
            sb.append(optimisedForSignallingTraffic);
            sb.append(", ");
            if (maximumBitRateForDownlinkExtended != null) {
                sb.append("maximumBitRateForDownlinkExtended=");
                sb.append(maximumBitRateForDownlinkExtended);
                sb.append(", ");
            }
            if (guaranteedBitRateForDownlinkExtended != null) {
                sb.append("guaranteedBitRateForDownlinkExtended=");
                sb.append(guaranteedBitRateForDownlinkExtended);
                sb.append(", ");
            }
            sb.append("]");

            return sb.toString();
        } else {
            return super.toString();
        }
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<Ext2QoSSubscribedImpl> EXT2_QOS_SUBSCRIBED_XML = new XMLFormat<Ext2QoSSubscribedImpl>(Ext2QoSSubscribedImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, Ext2QoSSubscribedImpl qos2Subscribed) throws XMLStreamException {
            Ext2QoSSubscribed_SourceStatisticsDescriptor sourceStatisticsDescriptor =
                    Enum.valueOf(Ext2QoSSubscribed_SourceStatisticsDescriptor.class, xml.getAttribute(SOURCE_STATISTICS_DESCRIPTOR, "unknown"));
            boolean optimisedForSignallingTraffic = xml.getAttribute(OPTIMISED_FOR_SIGNALLING_TRAFFIC, DEFAULT_BOOL_VALUE);
            ExtQoSSubscribed_BitRateExtended maximumBitRateForDownlinkExtended = new ExtQoSSubscribed_BitRateExtendedImpl(xml.getAttribute(MAX_BIT_RATE_FOR_DOWNLINK_EXTENDED, DEFAULT_INT_VALUE), false);
            ExtQoSSubscribed_BitRateExtended guaranteedBitRateForDownlinkExtended = new ExtQoSSubscribed_BitRateExtendedImpl(xml.getAttribute(GUARANTEED_BIT_RATE_FOR_DOWNLINK_EXTENDED, DEFAULT_INT_VALUE), false);
            qos2Subscribed.setData(sourceStatisticsDescriptor, optimisedForSignallingTraffic, maximumBitRateForDownlinkExtended, guaranteedBitRateForDownlinkExtended);
        }

        @Override
        public void write(Ext2QoSSubscribedImpl qos2Subscribed, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (qos2Subscribed.data != null) {
                if(qos2Subscribed.getSourceStatisticsDescriptor()!=null)
                    xml.setAttribute(SOURCE_STATISTICS_DESCRIPTOR, qos2Subscribed.getSourceStatisticsDescriptor().toString());
                xml.setAttribute(OPTIMISED_FOR_SIGNALLING_TRAFFIC, qos2Subscribed.isOptimisedForSignallingTraffic());
                if(qos2Subscribed.getMaximumBitRateForDownlinkExtended()!=null)
                    xml.setAttribute(MAX_BIT_RATE_FOR_DOWNLINK_EXTENDED, qos2Subscribed.getMaximumBitRateForDownlinkExtended().getBitRate());
                if(qos2Subscribed.getGuaranteedBitRateForDownlinkExtended()!=null)
                    xml.setAttribute(GUARANTEED_BIT_RATE_FOR_DOWNLINK_EXTENDED, qos2Subscribed.getGuaranteedBitRateForDownlinkExtended().getBitRate());
            }
        }
    };
}
