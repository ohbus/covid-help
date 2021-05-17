/*
 * The MIT License
 * Copyright © 2021 Subhrodip Mohanta
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package xyz.subho.covidhelp.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.subho.covidhelp.entity.OxygenLead;
import xyz.subho.covidhelp.repository.OxygenRepository;
import xyz.subho.covidhelp.service.OxygenService;

@Service
@Transactional
@Slf4j
public class OxygenServiceImpl implements OxygenService {

  @Autowired private OxygenRepository oxygenRepository;

  @Override
  public void UpdateOxygenLead(OxygenLead oxygen, OxygenLead dupOxygen) {
    dupOxygen.setOxyPropName(oxygen.getOxyPropName());
    dupOxygen.setOxyPropContactPrimary(oxygen.getOxyPropContactPrimary());
    dupOxygen.setOxyPropContactSecondary(oxygen.getOxyPropContactSecondary());
    dupOxygen.setLocationLat(oxygen.getLocationLat());
    dupOxygen.setLocationLon(oxygen.getLocationLon());
    dupOxygen.setLocationAcu(oxygen.getLocationAcu());
    dupOxygen.setLocationTimestamp(oxygen.getLocationTimestamp());
    dupOxygen.setLocationUrl(oxygen.getLocationUrl());
    dupOxygen.setLastVerifiedAt(oxygen.getLastVerifiedAt());
    dupOxygen.setVerifiedCount(oxygen.getVerifiedCount());
    dupOxygen.setLastUnavailableAt(oxygen.getLastUnavailableAt());
    dupOxygen.setUnavailableCount(oxygen.getUnavailableCount());
    oxygenRepository.save(dupOxygen);
  }

  @Override
  public void newOxygenLead(OxygenLead oxygen) {
    String primaryCont = oxygen.getOxyPropContactPrimary();
    try {
      // TODO: Duplicate Oxygen Lead
      OxygenLead dupOxygen = oxygenRepository.findByOxyPropContactPrimary(primaryCont);
      if (dupOxygen.getOxyPropContactPrimary().equals(primaryCont))
    	  UpdateOxygenLead(oxygen, dupOxygen);
      else 
    	  oxygenRepository.save(oxygen);
    } catch (Exception e) {
      log.info(e.toString());
    }
  }

	@Override
	public OxygenLead getOxygenById(Long OxyId) {
		// TODO Auto-generated method stub
		OxygenLead ox= getOxygenById(OxyId);
		return ox;
		
	}
}
