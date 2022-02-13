package com.iprogrammer.photocomparisonspa.utils

import java.io.IOException
class ApiException(message: String):IOException(message)
class NoIntenetException(message: String):IOException(message)
class ServerUnreachableException(message: String):IOException(message)
