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

package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

/**
 *
 CallReferenceNumber ::= OCTET STRING (SIZE (1..8))
 *
 * The use of this parameter and the conditions for its presence are specified in 3GPP TS 23.078 [98] and 3GPP TS 23.079 [99].
 *
 * This parameter gives the call reference number assigned to the call by the CCF. For encoding see GSM 09.02 [20].
 *
 * The CRN is an octet string with variable length between one and eight octets. An MSC uses an internal counter to generate the
 * CRN. Each CRN that is generated in an MSC is unique within that MSC. For every call that is established in an MSC, a CRN is
 * generated by that MSC. The CRN is strictly associated with the MSC that allocates the CRN.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallReferenceNumber extends Serializable {

    byte[] getData();

}