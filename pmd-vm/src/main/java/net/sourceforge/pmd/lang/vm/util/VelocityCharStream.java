
package net.sourceforge.pmd.lang.vm.util;

import net.sourceforge.pmd.lang.ast.CharStream;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 *  NOTE : This class was originally an ASCII_CharStream autogenerated
 *  by Javacc.  It was then modified via changing class name with appropriate
 *  fixes for CTORS, and mods to readChar().
 *
 *  This is safe because we *always* use Reader with this class, and never a
 *  InputStream.  This guarantees that we have a correct stream of 16-bit
 *  chars - all encoding transformations have been done elsewhere, so we
 *  believe that there is no risk in doing this.  Time will tell :)
 */

/**
 * An implementation of interface CharStream, where the stream is assumed to
 * contain only ASCII characters (without unicode processing).
 *
 * @deprecated Will be removed, replaced with SimpleCharStream
 */
@Deprecated
public final class VelocityCharStream implements CharStream {
    public static final boolean STATIC_FLAG = false;
    int bufsize;
    private int nextBufExpand;
    int available;
    int tokenBegin;

    public int bufpos = -1;
    private int[] bufline;
    private int[] bufcolumn;

    private int column = 0;
    private int line = 1;

    private boolean prevCharIsCR = false;
    private boolean prevCharIsLF = false;

    private java.io.Reader inputStream;

    private char[] buffer;
    private int maxNextCharInd = 0;
    private int inBuf = 0;

    /**
     * @param dstream
     * @param startline
     * @param startcolumn
     * @param buffersize
     */
    public VelocityCharStream(java.io.InputStream dstream, int startline, int startcolumn, int buffersize) {
        this(new java.io.InputStreamReader(dstream), startline, startcolumn, buffersize);
    }

    /**
     * @param dstream
     * @param startline
     * @param startcolumn
     */
    public VelocityCharStream(java.io.InputStream dstream, int startline, int startcolumn) {
        this(dstream, startline, startcolumn, 4096);
    }

    /**
     * @param dstream
     * @param startline
     * @param startcolumn
     * @param buffersize
     */
    public VelocityCharStream(java.io.Reader dstream, int startline, int startcolumn, int buffersize) {
        inputStream = dstream;
        line = startline;
        column = startcolumn - 1;

        available = buffersize;
        bufsize = buffersize;
        nextBufExpand = buffersize;
        buffer = new char[buffersize];
        bufline = new int[buffersize];
        bufcolumn = new int[buffersize];
    }

    /**
     * @param dstream
     * @param startline
     * @param startcolumn
     */
    public VelocityCharStream(java.io.Reader dstream, int startline, int startcolumn) {
        this(dstream, startline, startcolumn, 4096);
    }

    private void expandBuff(boolean wrapAround) {
        char[] newbuffer = new char[bufsize + nextBufExpand];
        int[] newbufline = new int[bufsize + nextBufExpand];
        int[] newbufcolumn = new int[bufsize + nextBufExpand];

        if (wrapAround) {
            System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
            System.arraycopy(buffer, 0, newbuffer, bufsize - tokenBegin, bufpos);
            buffer = newbuffer;

            System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
            System.arraycopy(bufline, 0, newbufline, bufsize - tokenBegin, bufpos);
            bufline = newbufline;

            System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
            System.arraycopy(bufcolumn, 0, newbufcolumn, bufsize - tokenBegin, bufpos);
            bufcolumn = newbufcolumn;

            bufpos += bufsize - tokenBegin;
            maxNextCharInd = bufpos;
        } else {
            System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
            buffer = newbuffer;

            System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
            bufline = newbufline;

            System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
            bufcolumn = newbufcolumn;

            bufpos -= tokenBegin;
            maxNextCharInd = bufpos;
        }

        bufsize += nextBufExpand;
        nextBufExpand = bufsize;
        available = bufsize;
        tokenBegin = 0;
    }

    private void fillBuff() throws java.io.IOException {
        if (maxNextCharInd == available) {
            if (available == bufsize) {
                if (tokenBegin > nextBufExpand) {
                    bufpos = 0;
                    maxNextCharInd = 0;
                    available = tokenBegin;
                } else if (tokenBegin < 0) {
                    bufpos = 0;
                    maxNextCharInd = 0;
                } else {
                    expandBuff(false);
                }
            } else if (available > tokenBegin) {
                available = bufsize;
            } else if ((tokenBegin - available) < nextBufExpand) {
                expandBuff(true);
            } else {
                available = tokenBegin;
            }
        }

        int i;
        try {
            i = inputStream.read(buffer, maxNextCharInd, available - maxNextCharInd);
            if (i == -1) {
                inputStream.close();
                throw new java.io.IOException();
            } else {
                maxNextCharInd += i;
            }
            return;
        } catch (java.io.IOException e) {
            --bufpos;
            backup(0);
            if (tokenBegin == -1) {
                tokenBegin = bufpos;
            }
            throw e;
        }
    }

    @Override
    public char BeginToken() throws java.io.IOException {
        tokenBegin = -1;
        char c = readChar();
        tokenBegin = bufpos;

        return c;
    }

    private void updateLineColumn(char c) {
        column++;

        if (prevCharIsLF) {
            prevCharIsLF = false;
            column = 1;
            line += 1;
        } else if (prevCharIsCR) {
            prevCharIsCR = false;
            if (c == '\n') {
                prevCharIsLF = true;
            } else {
                column = 1;
                line += 1;
            }
        }

        switch (c) {
        case '\r':
            prevCharIsCR = true;
            break;
        case '\n':
            prevCharIsLF = true;
            break;
        case '\t':
            column--;
            column += 8 - (column & 07);
            break;
        default:
            break;
        }

        bufline[bufpos] = line;
        bufcolumn[bufpos] = column;
    }

    @Override
    public char readChar() throws java.io.IOException {
        if (inBuf > 0) {
            --inBuf;

            /*
             * was : return (char)((char)0xff & buffer[(bufpos == bufsize - 1) ?
             * (bufpos = 0) : ++bufpos]);
             */
            if (bufpos == bufsize - 1) {
                bufpos = 0;
            } else {
                bufpos++;
            }
            return buffer[bufpos];
        }

        bufpos++;
        if (bufpos >= maxNextCharInd) {
            fillBuff();
        }

        /*
         * was : char c = (char)((char)0xff & buffer[bufpos]);
         */
        char c = buffer[bufpos];

        updateLineColumn(c);
        return c;
    }

    @Override
    public int getEndColumn() {
        return bufcolumn[bufpos];
    }

    @Override
    public int getEndLine() {
        return bufline[bufpos];
    }

    @Override
    public int getBeginColumn() {
        return bufcolumn[tokenBegin];
    }

    @Override
    public int getBeginLine() {
        return bufline[tokenBegin];
    }

    @Override
    public void backup(int amount) {

        inBuf += amount;
        bufpos -= amount;
        if (bufpos < 0) {
            bufpos += bufsize;
        }
    }

    /**
     * @param dstream
     * @param startline
     * @param startcolumn
     * @param buffersize
     */
    public void reInit(java.io.Reader dstream, int startline, int startcolumn, int buffersize) {
        inputStream = dstream;
        line = startline;
        column = startcolumn - 1;

        if (buffer == null || buffersize != buffer.length) {
            available = buffersize;
            bufsize = buffersize;
            nextBufExpand = buffersize;
            buffer = new char[buffersize];
            bufline = new int[buffersize];
            bufcolumn = new int[buffersize];
        }
        prevCharIsLF = false;
        prevCharIsCR = false;
        tokenBegin = 0;
        inBuf = 0;
        maxNextCharInd = 0;
        bufpos = -1;
    }

    /**
     * @param dstream
     * @param startline
     * @param startcolumn
     */
    public void reInit(java.io.Reader dstream, int startline, int startcolumn) {
        reInit(dstream, startline, startcolumn, 4096);
    }

    /**
     * @param dstream
     * @param startline
     * @param startcolumn
     * @param buffersize
     */
    public void reInit(java.io.InputStream dstream, int startline, int startcolumn, int buffersize) {
        reInit(new java.io.InputStreamReader(dstream), startline, startcolumn, buffersize);
    }

    /**
     * @param dstream
     * @param startline
     * @param startcolumn
     */
    public void reInit(java.io.InputStream dstream, int startline, int startcolumn) {
        reInit(dstream, startline, startcolumn, 4096);
    }

    @Override
    public String GetImage() {
        if (bufpos >= tokenBegin) {
            return new String(buffer, tokenBegin, bufpos - tokenBegin + 1);
        } else {
            return new String(buffer, tokenBegin, bufsize - tokenBegin) + new String(buffer, 0, bufpos + 1);
        }
    }

    @Override
    public char[] GetSuffix(int len) {
        char[] ret = new char[len];

        if ((bufpos + 1) >= len) {
            System.arraycopy(buffer, bufpos - len + 1, ret, 0, len);
        } else {
            System.arraycopy(buffer, bufsize - (len - bufpos - 1), ret, 0, len - bufpos - 1);
            System.arraycopy(buffer, 0, ret, len - bufpos - 1, bufpos + 1);
        }

        return ret;
    }


    /**
     * Method to adjust line and column numbers for the start of a token.<BR>
     *
     * @param newLine
     * @param newCol
     */
    public void adjustBeginLineColumn(int newLine, int newCol) {
        int start = tokenBegin;
        int len;

        if (bufpos >= tokenBegin) {
            len = bufpos - tokenBegin + inBuf + 1;
        } else {
            len = bufsize - tokenBegin + bufpos + 1 + inBuf;
        }

        int i = 0;
        int j = start % bufsize;
        int k = (start + 1) % bufsize;
        int nextColDiff = 0;
        int columnDiff = 0;

        while (i < len && bufline[j] == bufline[k]) {
            bufline[j] = newLine;
            nextColDiff = columnDiff + bufcolumn[k] - bufcolumn[j];
            bufcolumn[j] = newCol + columnDiff;
            columnDiff = nextColDiff;
            i++;

            start++;
            j = start % bufsize;
            k = (start + 1) % bufsize;
        }

        if (i < len) {
            bufline[j] = newLine++;
            bufcolumn[j] = newCol + columnDiff;

            while (i++ < len) {
                j = start % bufsize;
                start++;
                if (bufline[j] != bufline[start % bufsize]) {
                    bufline[j] = newLine++;
                } else {
                    bufline[j] = newLine;
                }
            }
        }

        line = bufline[j];
        column = bufcolumn[j];
    }

}
