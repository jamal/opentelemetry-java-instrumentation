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

package io.opentelemetry.instrumentation.api.typedspan;

import io.opentelemetry.trace.Span;

public interface FaasHttpSemanticConvention {
  void end();

  Span getSpan();

  /**
   * Sets a value for faas.trigger
   *
   * @param faasTrigger Type of the trigger on which the function is executed.
   */
  FaasHttpSemanticConvention setFaasTrigger(String faasTrigger);

  /**
   * Sets a value for faas.execution
   *
   * @param faasExecution The execution id of the current function execution.
   */
  FaasHttpSemanticConvention setFaasExecution(String faasExecution);

  /**
   * Sets a value for http.method
   *
   * @param httpMethod HTTP request method.
   */
  FaasHttpSemanticConvention setHttpMethod(String httpMethod);

  /**
   * Sets a value for http.url
   *
   * @param httpUrl Full HTTP request URL in the form `scheme://host[:port]/path?query[#fragment]`.
   *     Usually the fragment is not transmitted over HTTP, but if it is known, it should be
   *     included nevertheless.
   */
  FaasHttpSemanticConvention setHttpUrl(String httpUrl);

  /**
   * Sets a value for http.target
   *
   * @param httpTarget The full request target as passed in a HTTP request line or equivalent.
   */
  FaasHttpSemanticConvention setHttpTarget(String httpTarget);

  /**
   * Sets a value for http.host
   *
   * @param httpHost The value of the [HTTP host
   *     header](https://tools.ietf.org/html/rfc7230#section-5.4). When the header is empty or not
   *     present, this attribute should be the same.
   */
  FaasHttpSemanticConvention setHttpHost(String httpHost);

  /**
   * Sets a value for http.scheme
   *
   * @param httpScheme The URI scheme identifying the used protocol.
   */
  FaasHttpSemanticConvention setHttpScheme(String httpScheme);

  /**
   * Sets a value for http.status_code
   *
   * @param httpStatusCode [HTTP response status
   *     code](https://tools.ietf.org/html/rfc7231#section-6).
   */
  FaasHttpSemanticConvention setHttpStatusCode(long httpStatusCode);

  /**
   * Sets a value for http.status_text
   *
   * @param httpStatusText [HTTP reason phrase](https://tools.ietf.org/html/rfc7230#section-3.1.2).
   */
  FaasHttpSemanticConvention setHttpStatusText(String httpStatusText);

  /**
   * Sets a value for http.flavor
   *
   * @param httpFlavor Kind of HTTP protocol used.
   *     <p>If `net.transport` is not specified, it can be assumed to be `IP.TCP` except if
   *     `http.flavor` is `QUIC`, in which case `IP.UDP` is assumed.
   */
  FaasHttpSemanticConvention setHttpFlavor(String httpFlavor);

  /**
   * Sets a value for http.user_agent
   *
   * @param httpUserAgent Value of the [HTTP
   *     User-Agent](https://tools.ietf.org/html/rfc7231#section-5.5.3) header sent by the client.
   */
  FaasHttpSemanticConvention setHttpUserAgent(String httpUserAgent);

  /**
   * Sets a value for http.request_content_length
   *
   * @param httpRequestContentLength The size of the request payload body in bytes. This is the
   *     number of bytes transferred excluding headers and is often, but not always, present as the
   *     [Content-Length](https://tools.ietf.org/html/rfc7230#section-3.3.2) header. For requests
   *     using transport encoding, this should be the compressed size.
   */
  FaasHttpSemanticConvention setHttpRequestContentLength(long httpRequestContentLength);

  /**
   * Sets a value for http.request_content_length_uncompressed
   *
   * @param httpRequestContentLengthUncompressed The size of the uncompressed request payload body
   *     after transport decoding. Not set if transport encoding not used.
   */
  FaasHttpSemanticConvention setHttpRequestContentLengthUncompressed(
      long httpRequestContentLengthUncompressed);

  /**
   * Sets a value for http.response_content_length
   *
   * @param httpResponseContentLength The size of the response payload body in bytes. This is the
   *     number of bytes transferred excluding headers and is often, but not always, present as the
   *     [Content-Length](https://tools.ietf.org/html/rfc7230#section-3.3.2) header. For requests
   *     using transport encoding, this should be the compressed size.
   */
  FaasHttpSemanticConvention setHttpResponseContentLength(long httpResponseContentLength);

  /**
   * Sets a value for http.response_content_length_uncompressed
   *
   * @param httpResponseContentLengthUncompressed The size of the uncompressed response payload body
   *     after transport decoding. Not set if transport encoding not used.
   */
  FaasHttpSemanticConvention setHttpResponseContentLengthUncompressed(
      long httpResponseContentLengthUncompressed);

  /**
   * Sets a value for http.server_name
   *
   * @param httpServerName The primary server name of the matched virtual host. This should be
   *     obtained via configuration. If no such configuration can be obtained, this attribute MUST
   *     NOT be set ( `net.host.name` should be used instead).
   *     <p>http.url is usually not readily available on the server side but would have to be
   *     assembled in a cumbersome and sometimes lossy process from other information (see e.g.
   *     open-telemetry/opentelemetry-python/pull/148). It is thus preferred to supply the raw data
   *     that is available.
   */
  FaasHttpSemanticConvention setHttpServerName(String httpServerName);

  /**
   * Sets a value for http.route
   *
   * @param httpRoute The matched route (path template).
   */
  FaasHttpSemanticConvention setHttpRoute(String httpRoute);

  /**
   * Sets a value for http.client_ip
   *
   * @param httpClientIp The IP address of the original client behind all proxies, if known (e.g.
   *     from
   *     [X-Forwarded-For](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/X-Forwarded-For)).
   *     <p>This is not necessarily the same as `net.peer.ip`, which would identify the
   *     network-level peer, which may be a proxy.
   */
  FaasHttpSemanticConvention setHttpClientIp(String httpClientIp);

  /**
   * Sets a value for net.transport
   *
   * @param netTransport Transport protocol used. See note below.
   */
  FaasHttpSemanticConvention setNetTransport(String netTransport);

  /**
   * Sets a value for net.peer.ip
   *
   * @param netPeerIp Remote address of the peer (dotted decimal for IPv4 or
   *     [RFC5952](https://tools.ietf.org/html/rfc5952) for IPv6).
   */
  FaasHttpSemanticConvention setNetPeerIp(String netPeerIp);

  /**
   * Sets a value for net.peer.port
   *
   * @param netPeerPort Remote port number.
   */
  FaasHttpSemanticConvention setNetPeerPort(long netPeerPort);

  /**
   * Sets a value for net.peer.name
   *
   * @param netPeerName Remote hostname or similar, see note below.
   */
  FaasHttpSemanticConvention setNetPeerName(String netPeerName);

  /**
   * Sets a value for net.host.ip
   *
   * @param netHostIp Like `net.peer.ip` but for the host IP. Useful in case of a multi-IP host.
   */
  FaasHttpSemanticConvention setNetHostIp(String netHostIp);

  /**
   * Sets a value for net.host.port
   *
   * @param netHostPort Like `net.peer.port` but for the host port.
   */
  FaasHttpSemanticConvention setNetHostPort(long netHostPort);

  /**
   * Sets a value for net.host.name
   *
   * @param netHostName Local hostname or similar, see note below.
   */
  FaasHttpSemanticConvention setNetHostName(String netHostName);
}
