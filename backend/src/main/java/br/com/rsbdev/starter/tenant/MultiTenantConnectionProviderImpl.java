package br.com.rsbdev.starter.tenant;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.stereotype.Component;

@Component
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider {

	private static final long serialVersionUID = -1672319708928314730L;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private DataSourceLookup dataSourceLookup;

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		System.out.println(dataSourceLookup);
		return dataSource.getConnection();
	}

	@Override
	public Connection getConnection(String tenant) throws SQLException {
		String tenantIdentifier = TenantContext.getCurrentTenant();

		final Connection connection = getAnyConnection();

		try {
			if (tenantIdentifier != null) {
				connection.createStatement().execute("USE " + tenantIdentifier);
			} else {
				connection.createStatement().execute("USE " + TenantIdentifierResolver.DEFAULT_TENANT_ID);
			}
		} catch (SQLException e) {
			throw new HibernateException("Problem setting schema to " + tenantIdentifier, e);
		}

		return connection;
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		
		try {
			connection.createStatement().execute("USE " + TenantIdentifierResolver.DEFAULT_TENANT_ID);
		} catch (SQLException e) {
			throw new HibernateException("Problem setting schema to " + tenantIdentifier, e);
		}
		
		connection.close();
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return true;
	}

}
