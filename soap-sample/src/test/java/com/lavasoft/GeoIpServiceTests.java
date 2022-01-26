package com.lavasoft;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

   @Test
   public void testMyIp() {
      GeoIPServiceSoap service = new GeoIPService().getGeoIPServiceSoap12();
      String ipLocation = service.getIpLocation("178.66.67.236");
      Assert.assertTrue(ipLocation.contains("RU"));
   }
}
