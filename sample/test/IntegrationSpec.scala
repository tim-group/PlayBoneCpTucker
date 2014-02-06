import com.timgroup.tucker.info.Status
import helper.TuckerReader
import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import helper.PlayFakeApp

class IntegrationSpec extends Specification with PlayFakeApp {
  "Status page" should {
    "contains component for the app version" in {
      val result = routeAndCall(FakeRequest("GET", "/info/status")).get

      val component = TuckerReader.componentFor(result)("version")

      component.status must be (Status.INFO)
    }

    "contains component for each datasource" in {
      val result = routeAndCall(FakeRequest("GET", "/info/status")).get

      val defaultComponent = TuckerReader.componentFor(result)("BoneCp-default")
      val specialComponent = TuckerReader.componentFor(result)("BoneCp-special")

      defaultComponent.status must be (Status.OK)
      specialComponent.status must be (Status.OK)
    }
  }
}