package com.example.demo_applicationinsights;

import com.azure.identity.AzureCliCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplicationinsightsApplication {

	@Value("${my.secret}")
	private String secret;

	@PostConstruct
	public void init() throws InterruptedException {
		SecretClient secretClient = new SecretClientBuilder()
				.vaultUrl("https://xiada-kv.vault.azure.net")
				.credential(new AzureCliCredentialBuilder().build())
				.buildClient();
		while (true) {
			KeyVaultSecret secret1 = secretClient.getSecret("my-secret");
			System.out.println("Secret1: " + secret1.getValue());
			Thread.sleep(30000);
			System.out.println("Secret: " + secret);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplicationinsightsApplication.class, args);
	}

}
