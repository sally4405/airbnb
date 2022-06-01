import Foundation

class NetworkManager {
    private let session = URLSession.shared

    func fetchData<T: Codable>(request: URLRequest, type: T.Type, completion: @escaping ((NetworkResult<T>) -> Void)) {
        let task = self.session.dataTask(with: request) { data, response, error in
            guard let response = response as? HTTPURLResponse else {
                completion(.failure(.unknownError))
                return
            }

            guard (200...399).contains(response.statusCode) else {
                completion(.failure(NetworkError(rawValue: response.statusCode) ?? .unknownError))
                return
            }

            guard let data = data else {
                completion(.failure(.unknownError))
                return
            }

            guard let result = try? JSONDecoder().decode(T.self, from: data) else {
                completion(.failure(.parsingError))
                return
            }

            completion(.success(result))
        }
        task.resume()
    }
}
