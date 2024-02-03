package de.schlueter.wave;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Wave
 */
public class Wave {
    private String ChunkID;
    private Integer ChunkSize;
    private String Format;
    private String Subchunk1ID;
    private Integer SubchunkSize;
    private Integer AudioFormat;
    private Integer NumChannels;
    private Integer SampleRate;
    private Integer ByteRate;
    private Integer BlockAlign;
    private Integer BitesPerSample;
    private String Subchunk2ID;
    private Integer DataSize;
    private byte[] Data;

    private Wave(WaveBuilder builder) {
        this.ChunkID = builder.ChunkID;
        this.ChunkSize = builder.ChunkSize;
        this.Format = builder.Format;
        this.Subchunk1ID = builder.Subchunk1ID;
        this.SubchunkSize = builder.SubchunkSize;
        this.AudioFormat = builder.AudioFormat;
        this.NumChannels = builder.NumChannels;
        this.SampleRate = builder.SampleRate;
        this.ByteRate = builder.ByteRate;
        this.BlockAlign = builder.BlockAlign;
        this.BitesPerSample = builder.BitesPerSample;
        this.Subchunk2ID = builder.Subchunk2ID;
        this.DataSize = builder.DataSize;
        this.Data = builder.Data;
    }

    public String getChunkID() {
        return ChunkID;
    }

    public String getFormat() {
        return Format;
    }

    public String getSubchunk1ID() {
        return Subchunk1ID;
    }

    public Integer getAudioFormat() {
        return AudioFormat;
    }

    public Integer getNumChannels() {
        return NumChannels;
    }

    public Integer getSampleRate() {
        return SampleRate;
    }

    public Integer getByteRate() {
        return ByteRate;
    }

    public Integer getBlockAlign() {
        return BlockAlign;
    }

    public Integer getBitesPerSample() {
        return BitesPerSample;
    }

    public String getSubchunk2ID() {
        return Subchunk2ID;
    }

    public Integer getDataSize() {
        return DataSize;
    }

    public byte[] getData() {
        return Data;
    }

    public static class WaveBuilder {
        private String ChunkID;
        private Integer ChunkSize;
        private String Format;
        private String Subchunk1ID;
        private Integer SubchunkSize;
        private Integer AudioFormat;
        private Integer NumChannels;
        private Integer SampleRate;
        private Integer ByteRate;
        private Integer BlockAlign;
        private Integer BitesPerSample;
        private String Subchunk2ID;
        private Integer DataSize;
        private byte[] Data;

        public WaveBuilder setChunkID(String chunkID) {
            this.ChunkID = chunkID;
            return this;
        }
        public WaveBuilder setChuckSize(Integer chunkSize) {
            this.ChunkSize = chunkSize;
            return this;
        }
        public WaveBuilder setFormat(String format) {
            this.Format = format;
            return this;
        }
        public WaveBuilder setSubchunk1ID(String subchunk1id) {
            this.Subchunk1ID = subchunk1id;
            return this;
        }
        public WaveBuilder setSubchunkSize(Integer SubchunkSize) {
            this.SubchunkSize = SubchunkSize;
            return this;
        }
        public WaveBuilder setAudioFormat(Integer audioFormat) {
            this.AudioFormat = audioFormat;
            return this;
        }
        public WaveBuilder setNumChannels(Integer numChannels) {
            this.NumChannels = numChannels;
            return this;
        }
        public WaveBuilder setSampleRate(Integer sampleRate) {
            this.SampleRate = sampleRate;
            return this;
        }
        public WaveBuilder setByteRate(Integer byteRate) {
            this.ByteRate = byteRate;
            return this;
        }
        public WaveBuilder setBlockAlign(Integer blockAlign) {
            this.BlockAlign = blockAlign;
            return this;
        }
        public WaveBuilder setBitesPerSample(Integer bitesPerSample) {
            this.BitesPerSample = bitesPerSample;
            return this;
        }
        public WaveBuilder setSubchunk2ID(String subchunk2id) {
            this.Subchunk2ID = subchunk2id;
            return this;
        }
        public WaveBuilder setDataSize(Integer dataSize) {
            this.DataSize = dataSize;
            return this;
        }
        public WaveBuilder setData(byte[] data) {
            this.Data = data;
            return this;
        }

        public Wave build() {
            return new Wave(this);
        }
    }

    public void create() {
        try (FileOutputStream out = new FileOutputStream("output.wav")) {
            writeString(out, ChunkID);
            writeInt(out, ChunkSize);
            writeString(out, Format);
            writeString(out, Subchunk1ID);
            writeInt(out, SubchunkSize);
            writeShort(out, (short)(int)AudioFormat);
            writeShort(out, (short)(int)NumChannels);
            writeInt(out, SampleRate);
            writeInt(out, ByteRate);
            writeShort(out, (short)(int)BlockAlign);
            writeShort(out, (short)(int)BitesPerSample);
            writeString(out, Subchunk2ID);
            writeInt(out, DataSize);
            out.write(Data);
            out.flush();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    private void writeString(FileOutputStream out, String value) throws IOException {
        out.write(value.getBytes());
    }

    // Utility method for writing an integer to the output stream
    private void writeInt(FileOutputStream out, int value) throws IOException {
        out.write((value >> 0) & 0xFF);
        out.write((value >> 8) & 0xFF);
        out.write((value >> 16) & 0xFF);
        out.write((value >> 24) & 0xFF);
    }

    // Utility method for writing a short to the output stream
    private void writeShort(FileOutputStream out, short value) throws IOException {
        out.write((value >> 0) & 0xFF);
        out.write((value >> 8) & 0xFF);
    }
}
