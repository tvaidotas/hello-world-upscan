/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.helloworldupscan.repository

import org.scalatest.{Matchers, WordSpec}
import reactivemongo.bson.BSONObjectID
import uk.gov.hmrc.helloworldupscan.connectors.Reference
import uk.gov.hmrc.helloworldupscan.model.{Failed, InProgress, UploadId, UploadedSuccessfully}

class UploadDetailsTest extends WordSpec with Matchers {

  "Serialization and deserialization of UploadDetails" should {

    "serialize and deserialize InProgress status" in {
      val input = UploadDetails(BSONObjectID.generate(), UploadId.generate, Reference("ABC"), InProgress)

      val serialized = UploadDetails.format.writes(input)
      val output = UploadDetails.format.reads(serialized)

      output.get shouldBe input
    }

    "serialize and deserialize Failed status" in {
      val input = UploadDetails(BSONObjectID.generate(), UploadId.generate, Reference("ABC"), Failed)

      val serialized = UploadDetails.format.writes(input)
      val output = UploadDetails.format.reads(serialized)

      output.get shouldBe input
    }

    "serialize and deserialize UploadedSuccessfully status" in {
      val input = UploadDetails(BSONObjectID.generate(), UploadId.generate, Reference("ABC"), UploadedSuccessfully("foo.txt", "text/plain", "http:localhost:8080"))

      val serialized = UploadDetails.format.writes(input)
      val output = UploadDetails.format.reads(serialized)

      output.get shouldBe input
    }
  }
}
