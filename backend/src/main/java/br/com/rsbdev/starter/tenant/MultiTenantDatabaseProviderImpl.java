package br.com.rsbdev.starter.tenant;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;

//@Component
public class MultiTenantDatabaseProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	private static final long serialVersionUID = 14535345L;

	@Autowired
	private DataSource defaultDataSource;
	
	@Autowired
	private DataSourceLookup dataSourceLookup;

	/**
	 * Select datasources in situations where not tenantId is used (e.g. startup
	 * processing).
	 */
	@Override
	protected DataSource selectAnyDataSource() {
		logger.trace("Select any dataSource: " + defaultDataSource);
		return defaultDataSource;
	}

	/**
	 * Obtains a DataSource based on tenantId
	 */
	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		DataSource ds = dataSourceLookup.getDataSource(tenantIdentifier);
		logger.trace("Select dataSource from " + tenantIdentifier + ": " + ds);
		return ds;
	}
}
