package com.optimagrowth.license.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.optimagrowth.license.config.ServiceConfig;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;

@Service
public class LicenseService {

	@Autowired
	MessageSource messages;

	@Autowired
	private LicenseRepository licenseRepository;

	@Autowired
	ServiceConfig config;


	public License getLicense(String licenseId, String organizationId){
		License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
		if (null == license) {
			throw new IllegalArgumentException(messages.getMessage("license.search.error.message",
					new String[] { licenseId, organizationId }, LocaleContextHolder.getLocale()));
		}
		return license.withComment(config.getProperty());
	}

	public License createLicense(License license){
		licenseRepository.save(license);

		return license.withComment(config.getProperty());
	}

	public License updateLicense(License license){
		licenseRepository.save(license);

		return license.withComment(config.getProperty());
	}

	public String deleteLicense(String licenseId){
		String responseMessage = null;
		licenseRepository.deleteById(licenseId);
		responseMessage = messages.getMessage("license.delete.message", new String[] { licenseId },
				LocaleContextHolder.getLocale());
		return responseMessage;

	}
}
