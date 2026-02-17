extends Node3D

const BASE_TCP_URL := "http://127.0.0.1:8000/upload"
var http_request: HTTPRequest

var worker := Thread.new()
var mutex := Mutex.new()

var raw_images: Array[Image] = []
var compressed_queue: Array[PackedByteArray] = []

var running: bool = true
var sending_data: bool = false


func _ready():
	self.init_http()
	self.worker.start(self._worker_thread)


func _exit_tree():
	self.running = false
	if self.worker.is_started():
		self.worker.wait_to_finish()


func init_http():
	var http := HTTPRequest.new()
	http.use_threads = true
	add_child(http)

	http.request_completed.connect(
		_on_request_completed
	)

	self.http_request = http


func take_screenshot():
	var img := get_viewport().get_texture().get_image()

	self.mutex.lock()
	self.raw_images.append(img)
	self.mutex.unlock()


func _on_take_screen_shot_timeout():
	self.take_screenshot()


func _worker_thread():
	while self.running:
		self.mutex.lock()
		if self.raw_images.is_empty():
			self.mutex.unlock()
			OS.delay_msec(1)
			continue

		var img = raw_images.pop_front()
		self.mutex.unlock()

		var bytes = img.save_png_to_buffer()
		var compressed = bytes.compress(FileAccess.COMPRESSION_DEFLATE)
		

		self.mutex.lock()
		self.compressed_queue.append(compressed)
		self.mutex.unlock()


func _process(_delta):
	self.mutex.lock()
	if self.sending_data or self.compressed_queue.is_empty():
		self.mutex.unlock()
		return
	
	var data = compressed_queue.pop_front()
	self.mutex.unlock()
	
	self._send_http(data)


func _send_http(data: PackedByteArray):
	var headers := ["Content-Type: application/octet-stream"]
	self.http_request.request_raw(
		BASE_TCP_URL,
		headers,
		HTTPClient.METHOD_POST,
		data
	)


func _on_request_completed(
	_result: int,
	_response_code: int,
	_headers: PackedStringArray,
	_body: PackedByteArray,
):
	print('???? adding back')
	
