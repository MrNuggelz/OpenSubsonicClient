package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
import io.github.mrnuggelz.opensubsonic.mockClient
import io.github.mrnuggelz.opensubsonic.responses.OpenSubsonicResponse
import io.github.mrnuggelz.opensubsonic.responses.StatusResponse
import io.kotest.core.spec.style.scopes.StringSpecRootScope
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe

fun <T> StringSpecRootScope.responseShouldBe(
    methodName: String,
    returnDescription: String,
    methodCall: suspend OpenSubsonicClient.() -> Result<T>,
    block: (T) -> Unit,
) {
    "$methodName should return $returnDescription" {
        methodCall(mockClient).shouldBeSuccess(block)
    }
}

fun <T, C : OpenSubsonicClient> StringSpecRootScope.responseShouldBe(
    methodName: String,
    returnDescription: String,
    client: C,
    methodCall: suspend C.() -> Result<T>,
    block: (T) -> Unit,
) {
    "$methodName should return $returnDescription" {
        methodCall(client).shouldBeSuccess(block)
    }
}

fun <T> StringSpecRootScope.expectResponse(
    methodName: String,
    returnDescription: String,
    expected: T,
    methodCall: suspend OpenSubsonicClient.() -> Result<T>,
) {
    "$methodName should return $returnDescription" {
        methodCall(mockClient).onFailure { it.printStackTrace() }.shouldBeSuccess {
            it shouldBe expected
        }
    }
}

fun <T, C : OpenSubsonicClient> StringSpecRootScope.expectResponse(
    methodName: String,
    returnDescription: String,
    expected: T,
    client: C,
    methodCall: suspend C.() -> Result<T>,
) {
    "$methodName should return $returnDescription" {
        methodCall(client).onFailure { it.printStackTrace() }.shouldBeSuccess {
            it shouldBe expected
        }
    }
}

fun <T> StringSpecRootScope.expectResponse(
    methodName: String,
    returnDescription: String,
    expected: Throwable,
    client: OpenSubsonicClient = mockClient,
    methodCall: suspend OpenSubsonicClient.() -> Result<T>,
) {
    "$methodName should return $returnDescription" {
        methodCall(client).shouldBeFailure {
            it shouldBe expected
        }
    }
}

val expectedOpenSubsonicResponse = OpenSubsonicResponse(
    type = "AwesomeServerName",
    serverVersion = "0.1.3 (tag)",
    openSubsonic = true,
    status = StatusResponse.OK,
    version = "1.16.1"
)
