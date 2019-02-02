package br.com.rsbdev.starter.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

	protected static final String DEFAULT_TENANT_ID = "rsbdev";

	@Override
	public String resolveCurrentTenantIdentifier() {
		String tenantId = TenantContext.getCurrentTenant();
        if (tenantId != null) {
            return tenantId;
        }
        return DEFAULT_TENANT_ID;	
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
