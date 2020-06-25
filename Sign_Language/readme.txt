1. Requirements
	Run these commands in terminal to install the required libraries.


	pip install tensorflow==1.2.1
	pip install matplotlib==2.0.2
	pip install numpy==1.13.0
	pip install opencv-python-headless


2. To start recognising gestures, run classify_webcam.py by typing

	python classify_webcam.py
   
   in the terminal



3. To retrain the model, delete the inception and logs folder, and run the following command in the terminal.

python train.py \
  --bottleneck_dir=logs/bottlenecks \
  --how_many_training_steps=2000 \
  --model_dir=inception \
  --summaries_dir=logs/output_summaries/basic \
  --output_graph=logs/output_graph.pb \
  --output_labels=logs/output_labels.txt \
  --image_dir=./dataset