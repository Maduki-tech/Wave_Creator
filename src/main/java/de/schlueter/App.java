package de.schlueter;

import de.schlueter.wave.Wave;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // WAV file parameters
        int sampleRate = 44100;    // Sample rate in Hz
        int numChannels = 1;       // Mono audio
        int bitsPerSample = 16;    // Bits per sample
        int durationInSeconds = 1; // Duration in seconds
        double frequency = 440.0;  // Frequency of the sine wave in Hz

        int numSamples = durationInSeconds * sampleRate;
        byte[] data = new byte[2 * numSamples]; // 2 bytes per sample

        data = generateData(numSamples, sampleRate, frequency);
        int subChunkSize = numSamples * numChannels * bitsPerSample / 8;

        Wave wave = new Wave.WaveBuilder()
                        .setChunkID("RIFF")
                        .setChuckSize(4 + (8 + 16) + (8 + subChunkSize))
                        .setFormat("WAVE")
                        .setSubchunk1ID("fmt ")
                        .setSubchunkSize(16)
                        .setAudioFormat(1)
                        .setNumChannels(1)
                        .setSampleRate(44100)
                        .setByteRate(44100 * 1 * 16 / 8)
                        .setBlockAlign(1 * 16 / 8)
                        .setBitesPerSample(8)
                        .setSubchunk2ID("data")
                        .setDataSize(subChunkSize)
                        .setData(data)
                        .build();

        wave.create();
    }

    private static byte[] generateData(int numSamples, int sampleRate, double frequence) {
        byte[] data = new byte[2 * numSamples];
        for (int i = 0; i < numSamples; i++) {
            double time = i / (double)sampleRate;
            double angle = 2 * Math.PI * frequence * time;
            short sample = (short)(Math.sin(angle) * Short.MAX_VALUE);

            data[2 * i] = (byte)(sample * 0xFF);
            data[2 * i + 1] = (byte)((sample >> 8) & 0xFF);
        }

        return data;
    }
}
