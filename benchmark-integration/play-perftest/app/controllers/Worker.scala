/*
 * Copyright The OpenTelemetry Authors
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

package controllers

import io.opentelemetry.OpenTelemetry
import io.opentelemetry.trace.Tracer
import java.util.concurrent.TimeUnit

object Worker {
  val TRACER: Tracer =
    OpenTelemetry.getTracerProvider.get("io.opentelemetry.auto")

  def doWork(workTimeMS: Long) = {
    val span = TRACER.spanBuilder("work").startSpan()
    val scope = TRACER.withSpan(span)
    try {
      if (span != null) {
        span.setAttribute("work-time", workTimeMS)
        span.setAttribute("info", "interesting stuff")
        span.setAttribute("additionalInfo", "interesting stuff")
      }
      val doneTimestamp = System.nanoTime + TimeUnit.MILLISECONDS.toNanos(
        workTimeMS
      )
      while ({
        System.nanoTime < doneTimestamp
      }) {
        // busy-wait to simulate work
      }
    } finally {
      span.end()
      scope.close()
    }
  }
}
