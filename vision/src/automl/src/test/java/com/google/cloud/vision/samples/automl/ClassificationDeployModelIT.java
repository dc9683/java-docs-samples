/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.vision.samples.automl;

import static com.google.common.truth.Truth.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClassificationDeployModelIT {
  private static final String PROJECT_ID = System.getenv("GOOGLE_CLOUD_PROJECT");
  private static final String MODEL_ID = "ICN3125115511348658176";
  private ByteArrayOutputStream bout;
  private PrintStream out;

  @Before
  public void setUp() {
    bout = new ByteArrayOutputStream();
    out = new PrintStream(bout);
    System.setOut(out);
  }

  @After
  public void tearDown() {
    System.setOut(null);
  }

  @Test
  public void testClassificationDeployModelApi() {
    ClassificationDeployModel.classificationDeployModel(PROJECT_ID, MODEL_ID);

    String got = bout.toString();
    assertThat(got).contains("Model deployment finished");

    ClassificationUndeployModel.classificationUndeployModel(PROJECT_ID, MODEL_ID);

    got = bout.toString();
    assertThat(got).contains("Model undeploy finished");
  }

  @Test
  public void testClassificationDeployModelNodeCountApi() {
    ClassificationDeployModelNodeCount.classificationDeployModelNodeCount(PROJECT_ID, MODEL_ID);

    String got = bout.toString();
    assertThat(got).contains("Model deployment on 2 nodes finished");

    ClassificationUndeployModel.classificationUndeployModel(PROJECT_ID, MODEL_ID);

    got = bout.toString();
    assertThat(got).contains("Model undeploy finished");
  }
}
