import Foundation

enum NetworkResult<T> {
    case success(T)
    case failure(NetworkError)
}

enum NetworkError: Int, Error {
    case badRequest = 400
    case authenticationFailed = 401
    case notFound = 404
    case internalServer = 500
    case endpointError = 503
    case timeout = 504
    case unknownError = 1000
    case parsingError
}
