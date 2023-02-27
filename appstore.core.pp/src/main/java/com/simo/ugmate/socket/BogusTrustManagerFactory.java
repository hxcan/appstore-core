package com.simo.ugmate.socket;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class BogusTrustManagerFactory {
	
	public static final X509TrustManager X509 = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] x509Certificates,
				String s) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] x509Certificates,
				String s) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}
	};
	
	public static final TrustManager[] X509_MANAGERS = new TrustManager[] { X509 };
}
